package br.com.blinkdev.leadsponge.end_points.campaign.repository;

import br.com.blinkdev.leadsponge.end_points.campaign.filter.CampaignFilters;
import br.com.blinkdev.leadsponge.end_points.campaign.entity.CampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepositoryQuery {
    Page<CampaignEntity> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable);
}
