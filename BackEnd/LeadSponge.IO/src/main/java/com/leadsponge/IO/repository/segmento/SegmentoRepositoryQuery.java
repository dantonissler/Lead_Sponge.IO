package com.leadsponge.IO.repository.segmento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.Filter.SegmentoFilter;

@Repository
public interface SegmentoRepositoryQuery {
	Page<Segmento> filtrar(SegmentoFilter segmentoFilter, Pageable pageable);
}
