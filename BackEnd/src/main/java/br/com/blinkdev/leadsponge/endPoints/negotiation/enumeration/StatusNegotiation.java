package br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusNegotiation {

    EMANDAMENTO("Em andamento"), VENDIDO("Vendido"), PERDIDA("Perdida"), PAUSADO("Pausado");

    private final String estatus;
}
