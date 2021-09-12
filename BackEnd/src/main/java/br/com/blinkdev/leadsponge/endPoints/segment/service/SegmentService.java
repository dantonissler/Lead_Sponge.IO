package br.com.blinkdev.leadsponge.endPoints.segment.service;

import br.com.blinkdev.leadsponge.endPoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endPoints.segment.filter.SegmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SegmentService {
    SegmentEntity salvar(SegmentEntity segmento);

    SegmentEntity atualizar(Long id, SegmentEntity segmento);

    SegmentEntity deletar(Long id);

    SegmentEntity detalhar(Long id);

    Page<SegmentEntity> filtrar(SegmentFilter negociacaoFilter, Pageable pageable);
}
