package com.leadsponge.IO.repository.negociacaoProduto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.repository.Filter.NegociacaoProdutoFilter;

public interface NegociacaoProdutoRepositoryQuery {
	Page<NegociacaoProduto> filtrar(NegociacaoProdutoFilter negociacaoFilter, Pageable pageable);
}
