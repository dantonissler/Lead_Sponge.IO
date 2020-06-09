package com.leadsponge.IO.services;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ResourceBadRequestException;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.RoleRepository;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Usuario save(Usuario usuario) {
		usuariovalidar(usuario);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(new HashSet<>(roleRepository.findAll()));
//		usuario.getRoles().forEach(c -> c.setUsuarios(usuario));
//		usuario.setRoles(new HashSet<>(usuario.getRoles()));
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioExistente(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		usuarioSalvo.setPassword(bCryptPasswordEncoder.encode(usuarioSalvo.getPassword()));
		return usuarioRepository.save(usuarioSalvo);
	}

	public void atualizarPropriedadeEnabled(Long id, Boolean enabled) {
		Usuario usuarioSalva = buscarUsuarioExistente(id);
		usuarioSalva.setEnabled(enabled);
		usuarioRepository.save(usuarioSalva);
	}

//	private void validarRole(Usuario usuario) {
//		Role role = null;
//		if (usuario.getAuthorities().g != null) {
//			role = roleRepository.getOne(usuario.getAuthorities());
//		}
//	}

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
