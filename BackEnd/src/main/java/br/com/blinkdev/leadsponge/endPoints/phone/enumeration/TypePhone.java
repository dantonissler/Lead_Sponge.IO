package br.com.blinkdev.leadsponge.endPoints.phone.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypePhone {

    COMERCIAL("Comercial"), RESIDENCIAL("Residencial"), PESSOAL("Pessoal"), FAX("Fax");

    private final String descricao;

}
