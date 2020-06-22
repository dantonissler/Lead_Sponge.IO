package com.leadsponge.IO.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.produto.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {

}
