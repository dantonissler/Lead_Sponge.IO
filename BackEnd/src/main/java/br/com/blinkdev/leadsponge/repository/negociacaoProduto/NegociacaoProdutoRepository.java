package br.com.blinkdev.leadsponge.repository.negociacaoProduto;

import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoProdutoRepository extends JpaRepository<NegociacaoProduto, Long>, NegociacaoProdutoRepositoryQuery{

}
