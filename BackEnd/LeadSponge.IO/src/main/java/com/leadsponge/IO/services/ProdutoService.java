package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.produto.Produto;

@Service
public interface ProdutoService {
	public Produto save(Produto produto);

	public void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade);

	public Produto atualizar(Long id, Produto produto);
}
