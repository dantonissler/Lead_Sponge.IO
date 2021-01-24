package com.leadsponge.IO.repository.Filter;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoFilter {

	private String nome;

	private String descricao;

	private BigDecimal valor;

}
