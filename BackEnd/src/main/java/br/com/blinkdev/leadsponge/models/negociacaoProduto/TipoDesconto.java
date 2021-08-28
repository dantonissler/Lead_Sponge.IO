package br.com.blinkdev.leadsponge.models.negociacaoProduto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDesconto {
	PORCENTAGEM("porcentagem"), VALOR("valor");

	private final String tipo;

}
