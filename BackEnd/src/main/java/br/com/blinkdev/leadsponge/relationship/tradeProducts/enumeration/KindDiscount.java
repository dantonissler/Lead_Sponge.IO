package br.com.blinkdev.leadsponge.relationship.tradeProducts.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KindDiscount {
    PORCENTAGEM("porcentagem"), VALOR("valor");

    private final String tipo;

}
