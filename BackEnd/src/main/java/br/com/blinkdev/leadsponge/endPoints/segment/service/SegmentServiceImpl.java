package br.com.blinkdev.leadsponge.endPoints.segment.service;

import br.com.blinkdev.leadsponge.endPoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endPoints.segment.filter.SegmentFilter;
import br.com.blinkdev.leadsponge.endPoints.segment.model.SegmentModel;
import br.com.blinkdev.leadsponge.endPoints.segment.modelAssembler.SegmentModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.segment.repository.SegmentRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class SegmentServiceImpl extends ErroMessage implements SegmentService {
    @Autowired
    private SegmentRepository repository;

    @Autowired
    private SegmentModelAssembler segmentModelAssembler;

    @Autowired
    private PagedResourcesAssembler<SegmentEntity> assembler;


    @Override
    public SegmentModel getById(Long id) {
        return repository.findById(id).map(segmentModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[segment]"));
    }

    @Override
    public PagedModel<SegmentModel> searchWithFilters(SegmentFilter segmentFilter, Pageable pageable) {
        return assembler.toModel(repository.filtrar(segmentFilter, pageable), segmentModelAssembler);
    }

    @Override
    public SegmentModel save(SegmentEntity segmentEntity) {
        return segmentModelAssembler.toModel(repository.save(segmentEntity));
    }

    @Override
    public SegmentModel patch(Long id, Map<Object, Object> fields) {
        SegmentEntity segmentEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[segment]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(SegmentEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, segmentEntity, value);
        });
        return save(segmentEntity);
    }

    @Override
    public SegmentModel delete(Long id) {
        SegmentEntity segmentoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "[segment]"));
        repository.deleteById(id);
        return segmentModelAssembler.toModel(segmentoSalvo);
    }


}
