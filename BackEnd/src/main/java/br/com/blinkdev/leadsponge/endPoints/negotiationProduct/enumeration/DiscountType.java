package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountType {
    PORCENTAGEM("porcentagem"), VALOR("valor");

    private final String tipo;

}
