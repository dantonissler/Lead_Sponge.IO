package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.exception.UsuarioInativaException;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.segmento.SegmentoRepository;

@Service
public class SegmentoService {
	@Autowired
	private SegmentoRepository segmentoRepository;
	
	public Segmento save(Segmento segmento) {
		segmentoValidar(segmento);
		return segmentoRepository.save(segmento);
	}
	
	public Segmento atualizar(Long id, Segmento segmento) {
		Segmento fonteSegmento = buscarSegmentoExistente(id);
		BeanUtils.copyProperties(segmento, fonteSegmento, "id");
		return segmentoRepository.save(fonteSegmento);
	}
	
	private Segmento buscarSegmentoExistente(Long id) {
		Optional<Segmento> segmentoSalvo = segmentoRepository.findById(id);
		if (!segmentoSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return segmentoSalvo.get();
	}
	
	private void segmentoValidar(Segmento segmento) {
		if (segmento == null) {
			throw new UsuarioInativaException();
		}
	}
}
