package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.repository.Filter.NegociacaoProdutoFilter;

@Service
public interface NegociacaoProdutoService {
	NegociacaoProduto salvar(NegociacaoProduto nProduto);

	NegociacaoProduto atualizar(Long id, NegociacaoProduto negociacaoProduto);

	NegociacaoProduto deletar(Long id);

	NegociacaoProduto detalhar(Long id);

	Page<NegociacaoProduto> filtrar(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable);
}
