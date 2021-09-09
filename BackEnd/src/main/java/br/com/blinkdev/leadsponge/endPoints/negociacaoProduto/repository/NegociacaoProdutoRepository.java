package br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.repository;

import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoProdutoRepository extends JpaRepository<NegociacaoProdutoEntity, Long>, NegociacaoProdutoRepositoryQuery{

}
