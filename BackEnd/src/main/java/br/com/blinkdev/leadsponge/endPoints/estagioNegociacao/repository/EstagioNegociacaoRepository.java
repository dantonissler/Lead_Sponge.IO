package br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioNegociacaoRepository extends JpaRepository<EstagioNegociacaoEntity, Long>, EstagioNegociacaoRepositoryQuery {

}
