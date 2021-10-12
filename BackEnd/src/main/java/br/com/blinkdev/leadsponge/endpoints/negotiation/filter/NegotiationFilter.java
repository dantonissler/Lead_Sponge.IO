package br.com.blinkdev.leadsponge.endpoints.negotiation.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegotiationFilter {
    private String nome;
    private Integer avaliacao;
    private BigDecimal valorTotal;
    private BigDecimal valorMensal;
    private BigDecimal valorUnico;
}
