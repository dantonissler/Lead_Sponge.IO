package com.leadsponge.IO.repository.Filter;

import java.math.BigDecimal;

public class ProdutoFilter {

	private String nome;

	private String descricao;

	private BigDecimal valor;

	public ProdutoFilter(String nome, String descricao, BigDecimal valor) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
