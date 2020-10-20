package com.leadsponge.IO.models.enumerate;

public enum EstatusNegociacao {
	EMANDAMENTO("Em andamento"),
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
