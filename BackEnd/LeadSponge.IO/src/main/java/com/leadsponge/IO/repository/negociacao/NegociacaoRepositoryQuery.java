package com.leadsponge.IO.repository.negociacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.repository.Filter.NegociacaoFilter;

@Repository
public interface NegociacaoRepositoryQuery {
	Page<Negociacao> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable);
}
