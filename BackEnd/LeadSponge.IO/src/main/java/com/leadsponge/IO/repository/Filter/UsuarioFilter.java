package com.leadsponge.IO.repository.Filter;

public class UsuarioFilter {
	private String nomeCompleto;
	private String email;
	private String username;

	public UsuarioFilter(String nomeCompleto, String email, String username) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.username = username;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
