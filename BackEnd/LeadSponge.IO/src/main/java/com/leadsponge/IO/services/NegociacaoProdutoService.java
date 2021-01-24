package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.repository.Filter.NegociacaoProdutoFilter;

@Service
public interface NegociacaoProdutoService {
	public NegociacaoProduto salvar(NegociacaoProduto nProduto);

	public NegociacaoProduto atualizar(Long id, NegociacaoProduto negociacaoProduto);

	public NegociacaoProduto deletar(Long id);

	public NegociacaoProduto detalhar(Long id);

	public Page<NegociacaoProduto> filtrar(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable);
}
