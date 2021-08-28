package br.com.blinkdev.leadsponge.repository.produto;

import br.com.blinkdev.leadsponge.models.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {

}
