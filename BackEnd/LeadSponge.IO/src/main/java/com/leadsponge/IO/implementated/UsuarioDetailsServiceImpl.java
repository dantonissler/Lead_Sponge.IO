package com.leadsponge.IO.implementated;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.security.UsuarioSistema;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new UsuarioSistema(usuario, getRoles(usuario));
	}
	
	private Collection<? extends GrantedAuthority> getRoles(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getAuthority().toUpperCase())));
		return authorities;
	}

}
