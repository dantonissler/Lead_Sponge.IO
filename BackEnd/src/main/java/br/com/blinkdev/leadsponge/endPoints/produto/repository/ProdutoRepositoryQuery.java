package br.com.blinkdev.leadsponge.endPoints.produto.repository;

import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.produto.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositoryQuery {
	Page<ProdutoEntity> filtrar(ProdutoFilter produtoFilter, Pageable pageable);
}
