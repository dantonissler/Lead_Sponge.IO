package br.com.blinkdev.leadsponge.endPoints.campanha.repository;

import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampanhaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaRepositoryQuery {
    Page<CampanhaEntity> filtrar(CampanhaFilter campanhaFilter, Pageable pageable);
}
