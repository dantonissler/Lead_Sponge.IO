package com.leadsponge.IO.repository.Filter;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegociacaoFilter {
	private String nome;
	private Integer avaliacao;
	private BigDecimal valorTotal;
	private BigDecimal valorMensal;
	private BigDecimal valorUnico;
}
