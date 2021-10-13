package br.com.blinkdev.leadsponge.end_points.negotiation.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KindRecidivism {
    SINGLE("Único"), RECURRENT("Recorrente");

    private final String kind;
}
