package com.leadsponge.IO.repository.negociacaoProduto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;

public interface NegociacaoProdutoRepository extends JpaRepository<NegociacaoProduto, Long>, NegociacaoProdutoRepositoryQuery{

}
