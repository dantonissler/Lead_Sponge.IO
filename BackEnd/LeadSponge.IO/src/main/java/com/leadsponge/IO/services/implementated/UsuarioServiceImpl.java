package com.leadsponge.IO.services.implementated;

import java.util.HashSet;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.models.usuario.UsuarioTO;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.services.UsuarioService;
import com.leadsponge.IO.storage.Disco;

@Service
public class UsuarioServiceImpl extends ErroMessage implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private Disco disco;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario atualizarUsuarioDTO(Long id, UsuarioTO usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(id);
		usuarioSalvo.getRoles().clear();
		usuarioSalvo.getRoles().addAll(usuario.getRoles());
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id", "roles");
		return repository.save(usuarioSalvo);
	}

	@Override
	public void removerImg(Long id) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		disco.remover(usuarioSalva.getUrlFoto());
		usuarioSalva.setFoto(null);
		usuarioSalva.setUrlFoto(null);
		repository.save(usuarioSalva);
	}

	@Override
	public void atualizarImg(Long id, String foto) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		usuarioSalva.setFoto(foto);
		usuarioSalva.setUrlFoto(disco.configurarUrlFoto(foto));
		repository.save(usuarioSalva);
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		validar(usuario);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		return repository.save(usuario);
	}

	@Override
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(id);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuarioSalvo.getRoles().clear();
		usuarioSalvo.getRoles().addAll(usuario.getRoles());
		usuarioSalvo.setRoles(new HashSet<>(usuarioSalvo.getRoles()));
		if (StringUtils.isBlank(usuario.getFoto()) && StringUtils.isBlank(usuarioSalvo.getFoto())) {
			disco.remover(usuarioSalvo.getUrlFoto());
		} else if (StringUtils.isBlank(usuario.getFoto()) && !usuario.getFoto().equals(usuarioSalvo.getFoto())) {
			disco.remover(usuario.getUrlFoto());
		}

		BeanUtils.copyProperties(usuario, usuarioSalvo, "id", "roles");
		return repository.save(usuarioSalvo);
	}

	@Override
	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		usuarioSalva.setEnabled(enabled);
		repository.save(usuarioSalva);
	}

	@Override
	public String findLoggedInLogin() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	private Usuario buscarUsuarioExistente(Long id) {
		Optional<Usuario> usuarioSalvo = repository.findById(id);
		if (!usuarioSalvo.isPresent()) {
			throw notFouldId(id, "o usuario");
		}
		return usuarioSalvo.get();
	}

	private void validar(Usuario usuario) {
		if (!usuario.getConfirmarPassword().equals(usuario.getPassword()))
			throw otherMensagemBadRequest("O campo de senha não corresponde com o confirmar senha.");
		else if (repository.findByUsername(usuario.getUsername()).isPresent())
			throw otherMensagemBadRequest("O nome de usuario já existe.");
	}
}
