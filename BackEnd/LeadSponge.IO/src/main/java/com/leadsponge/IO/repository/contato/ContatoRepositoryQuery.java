package com.leadsponge.IO.repository.contato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.repository.Filter.ContatoFilter;

@Repository
public interface ContatoRepositoryQuery {
	Page<Contato> filtrar(ContatoFilter contatoFilter, Pageable pageable);
}
