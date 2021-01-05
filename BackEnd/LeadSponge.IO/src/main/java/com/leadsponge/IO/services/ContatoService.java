package com.leadsponge.IO.services;

import com.leadsponge.IO.models.contato.Contato;

public interface ContatoService {
	public Contato salvar(Contato contato);

	public Contato atualizar(Long id, Contato contato);
}
