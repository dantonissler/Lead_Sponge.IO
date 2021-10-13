package br.com.blinkdev.leadsponge.end_points.segment.service;

import br.com.blinkdev.leadsponge.end_points.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.end_points.segment.filter.SegmentFilter;
import br.com.blinkdev.leadsponge.end_points.segment.model.SegmentModel;
import br.com.blinkdev.leadsponge.end_points.segment.model_assembler.SegmentModelAssembler;
import br.com.blinkdev.leadsponge.end_points.segment.repository.SegmentRepository;
import br.com.blinkdev.leadsponge.error_validate.ErroMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SegmentServiceImpl extends ErroMessage implements SegmentService {
    private final SegmentRepository repository;
    private final SegmentModelAssembler segmentModelAssembler;
    private final PagedResourcesAssembler<SegmentEntity> assembler;


    @Override
    public SegmentModel getById(Long id) {
        return repository.findById(id).map(segmentModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[segment]"));
    }

    @Override
    public PagedModel<SegmentModel> searchWithFilters(SegmentFilter segmentFilter, Pageable pageable) {
        return assembler.toModel(repository.filtrar(segmentFilter, pageable), segmentModelAssembler);
    }

    @Override
    @Transactional
    public SegmentModel save(SegmentEntity segmentEntity) {
        return segmentModelAssembler.toModel(repository.save(segmentEntity));
    }

    @Override
    @Transactional
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
    @Transactional
    public SegmentModel delete(Long id) {
        SegmentEntity segmentoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "[segment]"));
        repository.deleteById(id);
        return segmentModelAssembler.toModel(segmentoSalvo);
    }


}
