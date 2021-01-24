package com.leadsponge.IO.models.negociacaoProduto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDesconto {
	PORCENTAGEM("porcentagem"), VALOR("valor");

	private final String tipo;

}
