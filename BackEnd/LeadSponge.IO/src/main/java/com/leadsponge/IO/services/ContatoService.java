package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.repository.Filter.ContatoFilter;

@Service
public interface ContatoService {
	public Contato salvar(Contato contato);

	public Contato atualizar(Long id, Contato contato);

	public Contato deletar(Long id);

	public Contato detalhar(Long id);

	public Page<Contato> filtrar(ContatoFilter clienteFilter, Pageable pageable);
}
