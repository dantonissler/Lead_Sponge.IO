package br.com.blinkdev.leadsponge.repository.campanha;

import br.com.blinkdev.leadsponge.models.campanha.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long>, CampanhaRepositoryQuery {
//	Iterable<Negociacao> findByNegociacoesCampanha(Negociacao contato);
}
