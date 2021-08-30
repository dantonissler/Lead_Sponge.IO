package br.com.blinkdev.leadsponge.models.negociacaoProduto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegociacaoProdutoFilter {
    private Integer quantidade;
    private BigDecimal valor;
    private BigDecimal total;
}
