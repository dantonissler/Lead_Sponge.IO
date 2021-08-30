package br.com.blinkdev.leadsponge.repository.segmento;

import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import br.com.blinkdev.leadsponge.models.segmento.SegmentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepositoryQuery {
	Page<Segmento> filtrar(SegmentoFilter segmentoFilter, Pageable pageable);
}
