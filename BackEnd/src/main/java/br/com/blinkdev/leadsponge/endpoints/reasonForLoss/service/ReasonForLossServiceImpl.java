package br.com.blinkdev.leadsponge.endpoints.reasonForLoss.service;

import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.filter.ReasonForLossFilter;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.model.ReasonForLossModel;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.modelAssembler.ReasonForLossModelAssembler;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.repository.ReasonForLossRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
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
public class ReasonForLossServiceImpl extends ErroMessage implements ReasonForLossService {
    private final ReasonForLossRepository repository;
    private final ReasonForLossModelAssembler reasonForLossModelAssembler;
    private final PagedResourcesAssembler<ReasonForLossEntity> assembler;

    @Override
    public ReasonForLossModel getById(Long id) {
        log.info("CustomerServiceImpl - getById");
        return repository.findById(id).map(reasonForLossModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[reason for loss]"));
    }

    @Override
    public PagedModel<ReasonForLossModel> searchWithFilters(ReasonForLossFilter motivoPerdaFilter, Pageable pageable) {
        log.info("CustomerServiceImpl - searchWithFilters");
        return assembler.toModel(repository.filtrar(motivoPerdaFilter, pageable), reasonForLossModelAssembler);
    }

    @Override
    @Transactional
    public ReasonForLossModel save(ReasonForLossEntity motivoPerda) {
        log.info("CustomerServiceImpl - save");
        return reasonForLossModelAssembler.toModel(repository.save(motivoPerda));
    }

    @Override
    @Transactional
    public ReasonForLossModel patch(Long id, Map<Object, Object> fields) {
        log.info("CustomerServiceImpl - patch");
        ReasonForLossEntity novomotivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "[reason for loss]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ReasonForLossEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, novomotivoPerda, value);
        });
        return save(novomotivoPerda);
    }

    @Override
    @Transactional
    public ReasonForLossModel delete(Long id) {
        log.info("CustomerServiceImpl - delete");
        ReasonForLossEntity motivoPerda = repository.findById(id).orElseThrow(() -> notFouldId(id, "[reason for loss]"));
        repository.deleteById(id);
        return reasonForLossModelAssembler.toModel(motivoPerda);
    }
}
