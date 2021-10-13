package br.com.blinkdev.leadsponge.end_points.negotiation.service;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.KindRecidivism;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.end_points.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.end_points.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.end_points.negotiation.model_assembler.NegotiationModelAssembler;
import br.com.blinkdev.leadsponge.end_points.negotiation.repository.NegotiationRepository;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.error_validate.ErroMessage;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NegotiationServiceImpl extends ErroMessage implements NegotiationService {
    private final NegotiationRepository repository;
    private final NegotiationModelAssembler negotiationModelAssembler;
    private final PagedResourcesAssembler<NegotiationEntity> assembler;

    @Override
    public NegotiationModel getById(Long id) {
        log.info("NegotiationService - getById");
        return repository.findById(id).map(negotiationModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[negotiation]"));
    }

    @Override
    public PagedModel<NegotiationModel> searchWithFilters(NegotiationFilter negociacaoFilter, Pageable pageable) {
        log.info("NegotiationService - searchWithFilters");
        return assembler.toModel(repository.filtrar(negociacaoFilter, pageable), negotiationModelAssembler);
    }

    @Override
    @Transactional
    public NegotiationModel save(NegotiationEntity negociacao) {
        log.info("NegotiationService - save");
        return negotiationModelAssembler.toModel(repository.save(negociacao));
    }

    @Override
    @Transactional
    public NegotiationModel patch(Long id, Map<Object, Object> fields) {
        log.info("NegotiationService - patch");
        NegotiationEntity negotiationEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        // TODO fazer as devidas validações
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(NegotiationEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, negotiationEntity, value);
        });
        return save(negotiationEntity);
    }

    @Override
    @Transactional
    public NegotiationModel deletar(Long id) {
        log.info("NegotiationService - deletar");
        NegotiationEntity negotiationEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        repository.deleteById(id);
        return negotiationModelAssembler.toModel(negotiationEntity);
    }

    @Override
    @Transactional
    public void updateNegotiationRFLE(Long id, ReasonForLossEntity motivoPerda) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        negociacaoSalva.setReasonForLoss(motivoPerda);
        negociacaoSalva.setStatus(StatusNegotiation.LOST);
        repository.save(negociacaoSalva);
    }

    @Override
    @Transactional
    public void updateNegotiationStatus(Long id, StatusNegotiation estatus) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        if (negociacaoSalva.getStatus() == StatusNegotiation.LOST)
            negociacaoSalva.setReasonForLoss(null);
        negociacaoSalva.setStatus(estatus);
        repository.save(negociacaoSalva);
    }

    @Override
    @Transactional
    public void updateNegotiationStyle(Long id, NegotiationStyleEntity estagio) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        // TODO: Criar o log utlizando o objeto HistoryNegotiationStyle
        negociacaoSalva.setNegotiationStyle(estagio);
        repository.save(negociacaoSalva);
    }

    @Override
    @Transactional
    public void atualizarPropriedadeDataFim(Long id, LocalDateTime data) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        negociacaoSalva.setExpectedClosingDate(data);
        repository.save(negociacaoSalva);
    }

    @Override
    @Transactional
    public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        negociacaoSalva.setEvaluation(avaliacao);
        repository.save(negociacaoSalva);
    }

    @Override
    @Transactional
    public void calculateValues(Long id) {
        NegotiationEntity negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        BigDecimal somaTotal = negociacao.getTradeProducts().stream().map(TradeProductsEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal somaMensal = negociacao.getTradeProducts().stream().filter(mensal -> mensal.getReincidencia().equals(KindRecidivism.RECURRENT)).map(TradeProductsEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal somaUnico = negociacao.getTradeProducts().stream().filter(mensal -> mensal.getReincidencia().equals(KindRecidivism.SINGLE)).map(TradeProductsEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        negociacao.setMonthlyValue(somaMensal);
        negociacao.setSingleValue(somaUnico);
        negociacao.setAmount(somaTotal);
        repository.save(negociacao);
    }
}
