package com.leadsponge.IO.models.enumerate;

public enum TipoDesconto {
	PORCENTAGEM("porcentagem"), VALOR("valor");

	private final String tipo;

	TipoDesconto(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return tipo;
	}
}
