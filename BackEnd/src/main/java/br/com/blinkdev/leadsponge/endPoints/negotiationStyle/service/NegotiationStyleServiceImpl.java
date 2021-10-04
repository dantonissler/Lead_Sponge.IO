package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.service;

import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.filter.NegotiationStyleFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.model.NegotiationStyleModel;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.modelAssembler.NegotiationStyleModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.repository.NegotiationStyleRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
public class NegotiationStyleServiceImpl extends ErroMessage implements NegotiationStyleService {

    @Autowired
    private NegotiationStyleRepository repository;

    @Autowired
    private NegotiationStyleModelAssembler negotiationStyleModelAssembler;

    @Autowired
    private PagedResourcesAssembler<NegotiationStyleEntity> assembler;

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
    public NegotiationStyleModel save(NegotiationStyleEntity estagioNegociacao) {
        log.info("NegotiationStyleServiceImpl - save");
        return negotiationStyleModelAssembler.toModel(repository.save(estagioNegociacao));
    }

    @Override
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
    public NegotiationStyleModel delete(Long id) {
        log.info("NegotiationStyleServiceImpl - delete");
        NegotiationStyleEntity negotiationStyleEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation style]"));
        repository.deleteById(id);
        return negotiationStyleModelAssembler.toModel(negotiationStyleEntity);
    }
}
