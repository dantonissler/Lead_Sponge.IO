package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.Filter.SegmentoFilter;

@Service
public interface SegmentoService {
	Segmento save(Segmento segmento);

	Segmento atualizar(Long id, Segmento segmento);

	Segmento deletar(Long id);

	Segmento detalhar(Long id);

	Page<Segmento> filtrar(SegmentoFilter negociacaoFilter, Pageable pageable);
}
