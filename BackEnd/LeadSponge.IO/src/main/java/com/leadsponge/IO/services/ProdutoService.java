package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.ProdutoFilter;

@Service
public interface ProdutoService {
	public Produto salvar(Produto produto);

	public void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade);

	public Produto atualizar(Long id, Produto produto);

	public Produto deletar(Long id);

	public Produto detalhar(Long id);

	public Page<Produto> filtrar(ProdutoFilter negociacaoFilter, Pageable pageable);
}
