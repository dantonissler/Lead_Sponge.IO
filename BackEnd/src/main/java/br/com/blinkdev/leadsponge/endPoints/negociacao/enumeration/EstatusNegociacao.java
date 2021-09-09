package br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstatusNegociacao {
	
	EMANDAMENTO("Em andamento"), VENDIDO("Vendido"), PERDIDA("Perdida"), PAUSADO("Pausado");

	private final String estatus;
}
