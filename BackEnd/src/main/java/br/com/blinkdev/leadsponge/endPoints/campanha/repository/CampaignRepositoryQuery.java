package br.com.blinkdev.leadsponge.endPoints.campanha.repository;

import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampaignFilters;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepositoryQuery {
    Page<CampaignEntity> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable);
}
