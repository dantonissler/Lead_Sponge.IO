package br.com.blinkdev.leadsponge.endPoints.produto.repository;

import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<ProdutoEntity, Long>, ProdutoRepositoryQuery {

}
