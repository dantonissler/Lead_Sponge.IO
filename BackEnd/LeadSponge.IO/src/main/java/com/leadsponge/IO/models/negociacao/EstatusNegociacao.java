package com.leadsponge.IO.models.negociacao;

public enum EstatusNegociacao {
	EMDAMENTO("Em andamento"),
	VENDIDO("Vendido"),
	PERDIDA("Perdida"),
	PAUSADO("Pausado");
	
	private final String estatus;
	
	EstatusNegociacao(String estatus) {
		this.estatus = estatus;
	}
	
	public String getDescricao() {
		return estatus;
	}
}
