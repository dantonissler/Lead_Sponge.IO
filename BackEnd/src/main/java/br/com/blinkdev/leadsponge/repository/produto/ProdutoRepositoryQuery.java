package br.com.blinkdev.leadsponge.repository.produto;

import br.com.blinkdev.leadsponge.models.produto.Produto;
import br.com.blinkdev.leadsponge.models.produto.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositoryQuery {
	Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);
}
