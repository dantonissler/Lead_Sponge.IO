package com.leadsponge.IO.repository.projection;

public class ResumoUsuario {
	/*
	 * TODO : Está funcionando a role porem tem que ser como lista para ficar a
	 * lista no nome do usuario. 
	 * Nesse casso ele repete o usuario varias vezes, pois é um join apenas.
	 */

	private Long id;
	private String username;
	private String nomeCompleto;
	private String email;
	private Boolean enabled;
//	private String role;

	public ResumoUsuario(Long id, String username, String nomeCompleto, String email/* , String role */, Boolean enabled) {
		this.id = id;
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
//		this.role = role;
		this.enabled = enabled;
	}

//	public String getRoles() {
//		return role;
//	}
//
//	public void setRoles(String role) {
//		this.role = role;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
