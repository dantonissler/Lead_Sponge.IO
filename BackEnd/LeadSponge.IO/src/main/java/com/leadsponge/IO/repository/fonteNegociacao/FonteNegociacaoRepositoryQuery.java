package com.leadsponge.IO.repository.fonteNegociacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.repository.Filter.FonteNegociacaoFilter;

@Repository
public interface FonteNegociacaoRepositoryQuery {
	Page<FonteNegociacao> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable);

}
