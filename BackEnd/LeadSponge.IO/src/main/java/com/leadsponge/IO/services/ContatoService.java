package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.repository.Filter.ContatoFilter;

@Service
public interface ContatoService {
	Contato salvar(Contato contato);

	Contato atualizar(Long id, Contato contato);

	Contato deletar(Long id);

	Contato detalhar(Long id);

	Page<Contato> filtrar(ContatoFilter clienteFilter, Pageable pageable);
}
