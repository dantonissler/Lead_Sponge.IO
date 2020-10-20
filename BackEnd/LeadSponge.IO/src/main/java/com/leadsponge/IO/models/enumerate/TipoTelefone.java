package com.leadsponge.IO.models.enumerate;

public enum TipoTelefone {
	
	COMERCIAL("Comercial"),
	RESIDENCIAL("Residencial"),
	PESSOAL("Pessoal"),
	FAX("Fax");
	
	private final String descricao;
	
	TipoTelefone(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}	
}
