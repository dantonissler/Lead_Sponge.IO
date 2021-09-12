package br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecidivismType {
    UNICO("Único"), RECORRENTE("Recorrente");

    private final String tipo;
}
