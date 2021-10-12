package br.com.blinkdev.leadsponge.endpoints.negotiationStyle.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegotiationStyleFilter {
    private String nome;
    private String apelido;
    private Integer posicao;
}
