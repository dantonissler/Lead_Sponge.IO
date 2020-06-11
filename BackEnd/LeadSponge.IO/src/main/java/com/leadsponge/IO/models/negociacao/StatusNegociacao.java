package com.leadsponge.IO.models.negociacao;

public enum StatusNegociacao {
	ANDAMENTO("Em andamento"),
	VENDIDO("Vendas"),
	PERDIDA("Negociação Perdidas"),
	PAUSADO("Pausado");
	
	private final String estatus;
	
	StatusNegociacao(String estatus) {
		this.estatus = estatus;
	}
	
	public String getDescricao() {
		return estatus;
	}
}
