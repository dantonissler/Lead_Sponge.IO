package br.com.blinkdev.leadsponge.endPoints.segment.service;

import br.com.blinkdev.leadsponge.endPoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endPoints.segment.filter.SegmentFilter;
import br.com.blinkdev.leadsponge.endPoints.segment.repository.SegmentRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SegmentServiceImpl extends ErroMessage implements SegmentService {
    @Autowired
    private SegmentRepository repository;

    @Override
    public SegmentEntity salvar(SegmentEntity segmento) {
        return repository.save(segmento);
    }

    @Override
    public SegmentEntity atualizar(Long id, SegmentEntity segmento) {
        SegmentEntity fonteSegmento = repository.findById(id).orElseThrow(() -> notFouldId(id, "a segmento"));
        BeanUtils.copyProperties(segmento, fonteSegmento, "id");
        return repository.save(fonteSegmento);
    }

    @Override
    public Page<SegmentEntity> filtrar(SegmentFilter segmentoFilter, Pageable pageable) {
        return repository.filtrar(segmentoFilter, pageable);
    }

    @Override
    public SegmentEntity deletar(Long id) {
        SegmentEntity segmentoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
        repository.deleteById(id);
        return segmentoSalvo;
    }

    @Override
    public SegmentEntity detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
    }


}
