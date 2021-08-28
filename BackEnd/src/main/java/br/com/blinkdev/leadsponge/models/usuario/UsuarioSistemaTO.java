package br.com.blinkdev.leadsponge.models.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistemaTO extends User {

	private static final long serialVersionUID = 1L;

	private Usuario username;

	public UsuarioSistemaTO(Usuario username, Collection<? extends GrantedAuthority> authorities) {
		super(username.getUsername(), username.getPassword(), authorities);
		this.username = username;
	}

	public Usuario getUsuario() {
		return username;
	}
}