package br.com.blinkdev.leadsponge.endPoints.negociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoRepository extends JpaRepository<NegociacaoEntity, Long>, NegociacaoRepositoryQuery {

}
