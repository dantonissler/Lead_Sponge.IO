package com.leadsponge.IO.errorValidate;

public abstract class ErroMessage{
	
	protected ResourceNotFoundException notFouldId(Long id, String nome) {
		return new ResourceNotFoundException("Não foi possivel encontrar "+nome+" com o id: " + id);
	}
	
	protected ResourceNotFoundException notFould(String nome) {
		return new ResourceNotFoundException("Não foi possivel encontrar "+nome);
	}
	
	protected ResourceNotFoundException notFouldError() {
		return new ResourceNotFoundException("Erro diverso, favor entrar em contato com o administrador da ferramenta.");
	}
}
