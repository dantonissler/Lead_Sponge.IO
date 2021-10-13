package br.com.blinkdev.leadsponge.end_points.negotiation_source.service;

import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.filter.NegotiationSourceFilter;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.model.NegotiationSourceModel;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.model_assembler.NegotiationSourceModelAssembler;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.repository.NegotiationSourceRepository;
import br.com.blinkdev.leadsponge.error_validate.ErroMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NegotiationSourceServiceImpl extends ErroMessage implements NegotiationSourceService {
    private final NegotiationSourceRepository negotiationSourceRepository;
    private final NegotiationSourceModelAssembler negotiationSourceModelAssembler;
    private final PagedResourcesAssembler<NegotiationSourceEntity> assembler;

    @Override
    public NegotiationSourceModel getById(Long id) {
        log.info("NegotiationSourceService - getById");
        return negotiationSourceRepository.findById(id).map(negotiationSourceModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[negotiation source]"));
    }

    @Override
    public PagedModel<NegotiationSourceModel> searchWithFilters(NegotiationSourceFilter negotiationSourceFilter, Pageable pageable) {
        log.info("NegotiationSourceService - searchWithFilters");
        return assembler.toModel(negotiationSourceRepository.filtrar(negotiationSourceFilter, pageable), negotiationSourceModelAssembler);
    }

    @Override
    @Transactional
    public NegotiationSourceModel save(NegotiationSourceEntity negotiationSourceEntity) {
        log.info("NegotiationSourceService - save");
        return negotiationSourceModelAssembler.toModel(negotiationSourceRepository.save(negotiationSourceEntity));
    }

    @Override
    @Transactional
    public NegotiationSourceModel patch(Long id, Map<Object, Object> fields) {
        log.info("NegotiationSourceService - patch");
        NegotiationSourceEntity negotiationSourceEntity = negotiationSourceRepository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation source]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(NegotiationSourceEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, negotiationSourceEntity, value);
        });
        return save(negotiationSourceEntity);
    }

    @Override
    @Transactional
    public NegotiationSourceModel delete(Long id) {
        log.info("CustomerService - delete");
        NegotiationSourceEntity fonteNegociacao = negotiationSourceRepository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation source]"));
        negotiationSourceRepository.deleteById(id);
        return negotiationSourceModelAssembler.toModel(fonteNegociacao);
    }
}
