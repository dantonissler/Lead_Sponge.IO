package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.Filter.SegmentoFilter;

@Service
public interface SegmentoService {
	public Segmento save(Segmento segmento);

	public Segmento atualizar(Long id, Segmento segmento);

	public Segmento deletar(Long id);

	public Segmento detalhar(Long id);

	public Page<Segmento> filtrar(SegmentoFilter negociacaoFilter, Pageable pageable);
}
