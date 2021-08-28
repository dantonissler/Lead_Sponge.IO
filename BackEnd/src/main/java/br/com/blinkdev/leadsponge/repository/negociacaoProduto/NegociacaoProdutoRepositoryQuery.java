package br.com.blinkdev.leadsponge.repository.negociacaoProduto;

import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import br.com.blinkdev.leadsponge.repository.Filter.NegociacaoProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoProdutoRepositoryQuery {
	Page<NegociacaoProduto> filtrar(NegociacaoProdutoFilter negociacaoFilter, Pageable pageable);
}
