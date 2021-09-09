package br.com.blinkdev.leadsponge.endPoints.segmento.service;

import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import br.com.blinkdev.leadsponge.endPoints.segmento.filter.SegmentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SegmentoService {
	SegmentoEntity salvar(SegmentoEntity segmento);

	SegmentoEntity atualizar(Long id, SegmentoEntity segmento);

	SegmentoEntity deletar(Long id);

	SegmentoEntity detalhar(Long id);

	Page<SegmentoEntity> filtrar(SegmentoFilter negociacaoFilter, Pageable pageable);
}
