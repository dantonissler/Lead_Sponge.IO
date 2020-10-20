package com.leadsponge.IO.models.enumerate;

public enum TipoTarefa {
	EMAIL("E-mail"),
	REUNIAO("Reunião"),
	VISITA("Visita"),
	TAREFA("Tarefa"),
	ALMOCO("Almoço");
	
	private final String descricao;
	
	TipoTarefa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
