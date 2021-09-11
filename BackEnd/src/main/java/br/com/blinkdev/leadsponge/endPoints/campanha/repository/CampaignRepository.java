package br.com.blinkdev.leadsponge.endPoints.campanha.repository;

import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignRepository extends CrudRepository<CampaignEntity, Long>, CampaignRepositoryQuery {
//	Iterable<Negociacao> findByNegociacoesCampanha(Negociacao contato);
}
