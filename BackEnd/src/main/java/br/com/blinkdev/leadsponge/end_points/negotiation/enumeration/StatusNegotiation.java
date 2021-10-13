package br.com.blinkdev.leadsponge.end_points.negotiation.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusNegotiation {
    SELLING("Em andamento"), VENDIDO("Vendido"), LOST("Perdida"), PAUSED("Pausado");
    private final String status;
}
