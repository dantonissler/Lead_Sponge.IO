package br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.repository;

import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoProdutoRepositoryQuery {
	Page<NegociacaoProdutoEntity> filtrar(NegociacaoProdutoFilter negociacaoFilter, Pageable pageable);
}
