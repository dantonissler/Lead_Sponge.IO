package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegotiationProductFilter {
    private Integer quantidade;
    private BigDecimal valor;
    private BigDecimal total;
}
