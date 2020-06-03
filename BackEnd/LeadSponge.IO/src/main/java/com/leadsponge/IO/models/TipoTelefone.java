package com.leadsponge.IO.models;

public enum TipoTelefone {
	
	COMERCIAL("comercial"),
	RESIDENCIAL("residencial"),
	PESSOAL("pessoal"),
	FAX("fax");
	
	private final String descricao;
	
	TipoTelefone(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}	
}
