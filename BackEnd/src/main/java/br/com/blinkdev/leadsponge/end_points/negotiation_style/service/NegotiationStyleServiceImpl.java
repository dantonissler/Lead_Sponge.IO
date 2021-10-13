package br.com.blinkdev.leadsponge.end_points.negotiation_style.service;

import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.filter.NegotiationStyleFilter;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.model.NegotiationStyleModel;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.model_assembler.NegotiationStyleModelAssembler;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.repository.NegotiationStyleRepository;
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
public class NegotiationStyleServiceImpl extends ErroMessage implements NegotiationStyleService {
    private final NegotiationStyleRepository repository;
    private final NegotiationStyleModelAssembler negotiationStyleModelAssembler;
    private final PagedResourcesAssembler<NegotiationStyleEntity> assembler;

    @Override
    public NegotiationStyleModel getById(Long id) {
        log.info("NegotiationStyleServiceImpl - getById");
        return repository.findById(id).map(negotiationStyleModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[negotiation style]"));
    }

    @Override
    public PagedModel<NegotiationStyleModel> searchWithFilters(NegotiationStyleFilter negotiationStyleFilter, Pageable pageable) {
        log.info("NegotiationStyleServiceImpl - searchWithFilters");
        return assembler.toModel(repository.filtrar(negotiationStyleFilter, pageable), negotiationStyleModelAssembler);
    }

    @Override
    @Transactional
    public NegotiationStyleModel save(NegotiationStyleEntity estagioNegociacao) {
        log.info("NegotiationStyleServiceImpl - save");
        return negotiationStyleModelAssembler.toModel(repository.save(estagioNegociacao));
    }

    @Override
    @Transactional
    public NegotiationStyleModel patch(Long id, Map<Object, Object> fields) {
        log.info("NegotiationStyleServiceImpl - patch");
        NegotiationStyleEntity negotiationStyleEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation style]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(NegotiationStyleEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, negotiationStyleEntity, value);
        });
        return save(negotiationStyleEntity);
    }

    @Override
    @Transactional
    public NegotiationStyleModel delete(Long id) {
        log.info("NegotiationStyleServiceImpl - delete");
        NegotiationStyleEntity negotiationStyleEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation style]"));
        repository.deleteById(id);
        return negotiationStyleModelAssembler.toModel(negotiationStyleEntity);
    }
}
