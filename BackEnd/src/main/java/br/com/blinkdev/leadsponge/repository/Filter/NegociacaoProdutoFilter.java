package br.com.blinkdev.leadsponge.repository.Filter;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegociacaoProdutoFilter {
	private Integer quantidade;
	private BigDecimal valor;
	private BigDecimal total;
}
