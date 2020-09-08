package com.leadsponge.IO.services;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.errorValidate.ResourceBadRequestException;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;


@Service
public class UsuarioService extends ErroMessage {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void removerImg(Long id) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
//		usuarioSalva.setAnexo(null);
		repository.save(usuarioSalva);
	}

	public void atualizarImg(Long id, String anexo) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
//		usuarioSalva.setAnexo(anexo);
		repository.save(usuarioSalva);
	}

	public Usuario save(Usuario usuario) {
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
//		if (StringUtils.isEmpty(usuario.getAnexo()) && StringUtils.hasText(usuarioSalvo.getAnexo())) {
//			s3.remover(usuarioSalvo.getAnexo());
//		} else if (StringUtils.hasText(usuario.getAnexo()) && !usuario.getAnexo().equals(usuarioSalvo.getAnexo())) {
//			s3.substituir(usuarioSalvo.getAnexo(), usuario.getAnexo());
//		}
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
