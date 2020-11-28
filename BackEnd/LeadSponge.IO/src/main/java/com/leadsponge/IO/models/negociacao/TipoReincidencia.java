package com.leadsponge.IO.models.negociacao;

public enum TipoReincidencia {
	UNICO("Único"), RECORRENTE("Recorrente");

	private final String tipo;

	TipoReincidencia(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return tipo;
	}
}
