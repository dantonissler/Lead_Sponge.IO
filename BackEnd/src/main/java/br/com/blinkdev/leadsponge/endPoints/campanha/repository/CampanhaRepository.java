package br.com.blinkdev.leadsponge.endPoints.campanha.repository;

import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampanhaRepository extends PagingAndSortingRepository<CampanhaEntity, Long>, CampanhaRepositoryQuery {
//	Iterable<Negociacao> findByNegociacoesCampanha(Negociacao contato);
}
