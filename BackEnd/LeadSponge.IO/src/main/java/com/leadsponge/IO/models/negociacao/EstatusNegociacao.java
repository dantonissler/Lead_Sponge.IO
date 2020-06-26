package com.leadsponge.IO.models.negociacao;

public enum EstatusNegociacao {
	ANDAMENTO("Em andamento"),
	VENDIDO("Vendas"),
	PERDIDA("Negociação Perdidas"),
	PAUSADO("Pausado");
	
	private final String estatus;
	
	EstatusNegociacao(String estatus) {
		this.estatus = estatus;
	}
	
	public String getDescricao() {
		return estatus;
	}
}
