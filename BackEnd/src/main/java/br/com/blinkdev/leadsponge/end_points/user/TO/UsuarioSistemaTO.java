package br.com.blinkdev.leadsponge.end_points.user.TO;

import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistemaTO extends User {

	private static final long serialVersionUID = 1L;

	private UserEntity username;

	public UsuarioSistemaTO(UserEntity username, Collection<? extends GrantedAuthority> authorities) {
		super(username.getUsername(), username.getPassword(), authorities);
		this.username = username;
	}

	public UserEntity getUsuario() {
		return username;
	}
}