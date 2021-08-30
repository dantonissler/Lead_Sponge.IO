package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.produto.Produto;
import br.com.blinkdev.leadsponge.models.produto.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProdutoService {
	Produto salvar(Produto produto);

	void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade);

	Produto atualizar(Long id, Produto produto);

	Produto deletar(Long id);

	Produto detalhar(Long id);

	Page<Produto> filtrar(ProdutoFilter negociacaoFilter, Pageable pageable);
}
