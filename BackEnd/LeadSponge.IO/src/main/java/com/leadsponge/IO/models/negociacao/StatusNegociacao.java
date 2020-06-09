package com.leadsponge.IO.models.negociacao;

public enum StatusNegociacao {
	CRIADA("Negociação Criadas"),
	VENDIDO("Vendas"),
	PERDIDA("Negociação Perdidas");
	
	private final String estatus;
	
	StatusNegociacao(String estatus) {
		this.estatus = estatus;
	}
	
	public String getDescricao() {
		return estatus;
	}
}
