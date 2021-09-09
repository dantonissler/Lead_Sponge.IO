package br.com.blinkdev.leadsponge.endPoints.segmento.repository;

import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import br.com.blinkdev.leadsponge.endPoints.segmento.filter.SegmentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepositoryQuery {
	Page<SegmentoEntity> filtrar(SegmentoFilter segmentoFilter, Pageable pageable);
}
