package br.com.blinkdev.leadsponge.repository.campanha;

import br.com.blinkdev.leadsponge.models.campanha.CampanhaEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampanhaRepository extends PagingAndSortingRepository<CampanhaEntity, Long>, CampanhaRepositoryQuery {
//	Iterable<Negociacao> findByNegociacoesCampanha(Negociacao contato);
}
