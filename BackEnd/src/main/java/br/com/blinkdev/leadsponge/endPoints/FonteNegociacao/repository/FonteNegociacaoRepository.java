package br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonteNegociacaoRepository extends JpaRepository<FonteNegociacaoEntity, Long>, FonteNegociacaoRepositoryQuery {

}
