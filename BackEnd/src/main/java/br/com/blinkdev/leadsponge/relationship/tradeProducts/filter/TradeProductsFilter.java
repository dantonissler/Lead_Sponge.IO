package br.com.blinkdev.leadsponge.relationship.tradeProducts.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeProductsFilter {
    private Integer quantidade;
    private BigDecimal valor;
    private BigDecimal total;
}
