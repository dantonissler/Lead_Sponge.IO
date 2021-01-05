package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.segmento.Segmento;

@Service
public interface SegmentoService {
	public Segmento save(Segmento segmento);

	public Segmento atualizar(Long id, Segmento segmento);

}
