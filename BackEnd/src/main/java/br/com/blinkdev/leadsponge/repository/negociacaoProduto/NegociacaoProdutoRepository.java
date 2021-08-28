package br.com.blinkdev.leadsponge.repository.negociacaoProduto;

import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegociacaoProdutoRepository extends JpaRepository<NegociacaoProduto, Long>, NegociacaoProdutoRepositoryQuery{

}
