package com.leadsponge.IO.repository.Filter;

public class TarefaFilter {
	private String assunto;
	
	public TarefaFilter(String assunto) {
		this.assunto = assunto;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
}
