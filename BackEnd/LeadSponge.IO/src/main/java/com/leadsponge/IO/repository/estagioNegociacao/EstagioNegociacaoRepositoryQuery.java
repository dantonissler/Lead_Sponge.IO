package com.leadsponge.IO.repository.estagioNegociacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;

public interface EstagioNegociacaoRepositoryQuery {
	Page<EstagioNegociacao> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable);
}
