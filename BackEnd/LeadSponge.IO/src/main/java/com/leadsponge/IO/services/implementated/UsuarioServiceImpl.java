package com.leadsponge.IO.services.implementated;

import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.models.usuario.UsuarioTO;
import com.leadsponge.IO.repository.Filter.UsuarioFilter;
import com.leadsponge.IO.repository.projection.ResumoUsuario;
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
		Usuario usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		usuarioSalva.getRoles().clear();
		usuarioSalva.getRoles().addAll(usuario.getRoles());
		BeanUtils.copyProperties(usuario, usuarioSalva, "id", "roles");
		return repository.save(usuarioSalva);
	}

	@Override
	public void removerImg(Long id) {
		Usuario usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		disco.remover(usuarioSalva.getUrlFoto());
		usuarioSalva.setFoto(null);
		usuarioSalva.setUrlFoto(null);
		repository.save(usuarioSalva);
	}

	@Override
	public void atualizarImg(Long id, String foto) {
		Usuario usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		usuarioSalva.setFoto(foto);
		usuarioSalva.setUrlFoto(disco.configurarUrlFoto(foto));
		repository.save(usuarioSalva);
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		if (!usuario.getConfirmarPassword().equals(usuario.getPassword()))
			throw otherMensagemBadRequest("O campo de senha não corresponde com o confirmar senha.");
		else if (repository.findByUsername(usuario.getUsername()).isPresent())
			throw otherMensagemBadRequest("O nome de usuario já existe.");
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		return repository.save(usuario);
	}

	@Override
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario"));
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuarioSalva.getRoles().clear();
		usuarioSalva.getRoles().addAll(usuario.getRoles());
		usuarioSalva.setRoles(new HashSet<>(usuarioSalva.getRoles()));
		if (StringUtils.isBlank(usuario.getFoto()) && StringUtils.isBlank(usuarioSalva.getFoto())) {
			disco.remover(usuarioSalva.getUrlFoto());
		} else if (StringUtils.isBlank(usuario.getFoto()) && !usuario.getFoto().equals(usuarioSalva.getFoto())) {
			disco.remover(usuario.getUrlFoto());
		}
		BeanUtils.copyProperties(usuario, usuarioSalva, "id", "roles");
		return repository.save(usuarioSalva);
	}

	@Override
	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		Usuario usuarioSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
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

	@Override
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		return repository.filtrar(usuarioFilter, pageable);
	}

	@Override
	public Usuario deletar(Long id) {
		Usuario usuarioSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
		repository.deleteById(id);
		return usuarioSalvo;
	}

	@Override
	public Usuario detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a produto"));
	}

	@Override
	public Page<ResumoUsuario> resumir(UsuarioFilter usuarioFilter, Pageable pageable) {
		return repository.resumir(usuarioFilter, pageable);
	}

	@Override
	public Usuario findByNome(String username) {
		return repository.findByUsername(username).orElseThrow(() -> notFould("o usuario"));
	}
}