package br.com.blinkdev.leadsponge.end_points.campaign.service;

import br.com.blinkdev.leadsponge.end_points.campaign.filter.CampaignFilters;
import br.com.blinkdev.leadsponge.end_points.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.end_points.campaign.model.CampaignModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CampaignService {

    CampaignModel getById(Long id);

    PagedModel<CampaignModel> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable);

    CampaignModel save(CampaignEntity campanha);

    CampaignModel patch(Long id, Map<Object, Object> fields);

    CampaignModel delete(Long id);
}
