package com.leadsponge.IO.repository.campanha;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;

@Repository
public interface CampanhaRepositoryQuery {
	Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable);
}
