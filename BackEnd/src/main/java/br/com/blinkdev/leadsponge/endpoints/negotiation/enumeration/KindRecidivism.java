package br.com.blinkdev.leadsponge.endpoints.negotiation.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KindRecidivism {
    UNICO("Único"), RECORRENTE("Recorrente");

    private final String tipo;
}
