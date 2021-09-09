package br.com.blinkdev.leadsponge.endPoints.tarefa.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTarefa {
	EMAIL("E-mail"), REUNIAO("Reunião"), VISITA("Visita"), TAREFA("Tarefa"), ALMOCO("Almoço");

	private final String descricao;

}
