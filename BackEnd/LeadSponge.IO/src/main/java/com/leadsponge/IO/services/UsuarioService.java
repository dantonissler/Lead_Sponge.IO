package com.leadsponge.IO.services;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.leadsponge.IO.dto.UsuarioDTO;
import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.errorValidate.ResourceBadRequestException;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;
import com.leadsponge.IO.storage.Disco;

@Service
public class UsuarioService extends ErroMessage {

	@Autowired
	private final UsuarioRepository repository;

	@Autowired
	private final Disco disco;

	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsuarioService(UsuarioRepository repository, Disco disco, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.repository = repository;
		this.disco = disco;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public Usuario atualizarUsuarioDTO(Long id, UsuarioDTO usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(id);
		usuarioSalvo.getRoles().clear();
		usuarioSalvo.getRoles().addAll(usuario.getRoles());
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id", "roles");
		return repository.save(usuarioSalvo);
	}

	public void removerImg(Long id) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		disco.remover(usuarioSalva.getUrlFoto());
		usuarioSalva.setFoto(null);
		usuarioSalva.setUrlFoto(null);
		repository.save(usuarioSalva);
	}

	public void atualizarImg(Long id, String foto) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		usuarioSalva.setFoto(foto);
		usuarioSalva.setUrlFoto(disco.configurarUrlFoto(foto));
		repository.save(usuarioSalva);
	}

	public Usuario salvar(Usuario usuario) {
		usuariovalidar(usuario);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		return repository.save(usuario);
	}

	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(id);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuarioSalvo.getRoles().clear();
		usuarioSalvo.getRoles().addAll(usuario.getRoles());
		usuarioSalvo.setRoles(new HashSet<>(usuarioSalvo.getRoles()));
		if (StringUtils.isEmpty(usuario.getFoto()) && StringUtils.hasText(usuarioSalvo.getFoto())) {
			disco.remover(usuarioSalvo.getUrlFoto());
		} else if (StringUtils.hasText(usuario.getFoto()) && !usuario.getFoto().equals(usuarioSalvo.getFoto())) {
			disco.remover(usuario.getUrlFoto());
		}

		BeanUtils.copyProperties(usuario, usuarioSalvo, "id", "roles");
		return repository.save(usuarioSalvo);
	}

	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		usuarioSalva.setEnabled(enabled);
		repository.save(usuarioSalva);
	}

	private Usuario buscarUsuarioExistente(Long id) {
		Optional<Usuario> usuarioSalvo = repository.findById(id);
		if (!usuarioSalvo.isPresent()) {
			throw notFould("o usuario");
		}
		return usuarioSalvo.get();
	}

	private void usuariovalidar(Usuario usuario) {
		if (usuario == null) {
			throw new UsuarioInativaException();
		}
		if (!usuario.getConfirmarPassword().equals(usuario.getPassword())) {
			throw new ResourceBadRequestException("O campo de senha não corresponde com o confirmar senha.");
		}
	}
}
