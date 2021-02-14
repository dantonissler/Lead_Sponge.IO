package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.ProdutoFilter;

@Service
public interface ProdutoService {
	Produto salvar(Produto produto);

	void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade);

	Produto atualizar(Long id, Produto produto);

	Produto deletar(Long id);

	Produto detalhar(Long id);

	Page<Produto> filtrar(ProdutoFilter negociacaoFilter, Pageable pageable);
}
