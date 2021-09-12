package br.com.blinkdev.leadsponge.endPoints.campaign.repository;

import br.com.blinkdev.leadsponge.endPoints.campaign.entity.CampaignEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignRepository extends CrudRepository<CampaignEntity, Long>, CampaignRepositoryQuery {
//	Iterable<Negociacao> findByNegociacoesCampanha(Negociacao contato);
}
