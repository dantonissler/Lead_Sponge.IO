package br.com.blinkdev.leadsponge.end_points.negotiation.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegotiationFilter {
    private String name;
    private Integer evaluation;
    private BigDecimal amount;
    private BigDecimal monthlyValue;
    private BigDecimal singleValue;
}
