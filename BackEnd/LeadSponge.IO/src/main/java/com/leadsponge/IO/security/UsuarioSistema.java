package com.leadsponge.IO.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.leadsponge.IO.models.usuario.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private Usuario username;

	public UsuarioSistema(Usuario username, Collection<? extends GrantedAuthority> authorities) {
		super(username.getUsername(), username.getPassword(), authorities);
		this.username = username;
	}

	public Usuario getUsuario() {
		return username;
	}
}
