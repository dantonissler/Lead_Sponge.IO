package com.leadsponge.IO.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.ProdutoFilter;

@Repository
public interface ProdutoRepositoryQuery {
	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);
}
