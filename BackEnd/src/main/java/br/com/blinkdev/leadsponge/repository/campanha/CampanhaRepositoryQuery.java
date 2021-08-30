package br.com.blinkdev.leadsponge.repository.campanha;

import br.com.blinkdev.leadsponge.models.campanha.Campanha;
import br.com.blinkdev.leadsponge.models.campanha.CampanhaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaRepositoryQuery {
	Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable);
}
