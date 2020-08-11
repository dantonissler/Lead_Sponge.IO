package com.leadsponge.IO.repository.Filter;

public class NegociacaoProdutoFilter {
	private String nome;
	
	public NegociacaoProdutoFilter(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
