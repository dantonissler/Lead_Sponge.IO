package com.leadsponge.IO.services;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.leadsponge.IO.errorValidate.ResourceBadRequestException;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;
import com.leadsponge.IO.storage.S3;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private S3 s3;


	public Usuario save(Usuario usuario) {
		usuariovalidar(usuario);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
//		usuario.setRoles(new HashSet<>(roleRepository.findAll()));
		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		if (StringUtils.hasText(usuario.getAnexo())) {
			s3.salvar(usuario.getAnexo());
		}
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(id);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuarioSalvo.getRoles().clear();
		usuarioSalvo.getRoles().addAll(usuario.getRoles());
		usuarioSalvo.setRoles(new HashSet<>(usuarioSalvo.getRoles()));

		if (StringUtils.isEmpty(usuario.getAnexo()) && StringUtils.hasText(usuarioSalvo.getAnexo())) {
			s3.remover(usuarioSalvo.getAnexo());
		} else if (StringUtils.hasText(usuario.getAnexo()) && !usuario.getAnexo().equals(usuarioSalvo.getAnexo())) {
			s3.substituir(usuarioSalvo.getAnexo(), usuario.getAnexo());
		}
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id", "roles");
		return usuarioRepository.save(usuarioSalvo);
	}

	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		usuarioSalva.setEnabled(enabled);
		usuarioRepository.save(usuarioSalva);
	}

	private Usuario buscarUsuarioExistente(Long id) {
		Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
		if (!usuarioSalvo.isPresent()) {
			throw new IllegalArgumentException();
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
