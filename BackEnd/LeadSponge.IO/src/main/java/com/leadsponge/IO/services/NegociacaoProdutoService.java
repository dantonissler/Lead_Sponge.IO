package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;

@Service
public interface NegociacaoProdutoService {
	public NegociacaoProduto salvar(NegociacaoProduto nProduto);

	public NegociacaoProduto atualizar(Long id, NegociacaoProduto negociacaoProduto);
}
