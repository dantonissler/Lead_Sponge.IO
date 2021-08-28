package br.com.blinkdev.leadsponge.models.negociacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoReincidencia {
	UNICO("Único"), RECORRENTE("Recorrente");

	private final String tipo;
}
