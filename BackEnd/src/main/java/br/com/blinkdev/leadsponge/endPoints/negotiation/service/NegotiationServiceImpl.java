package br.com.blinkdev.leadsponge.endPoints.negotiation.service;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.RecidivismType;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endPoints.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.endPoints.negotiation.modelAssembler.NegotiationModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.negotiation.repository.NegotiationRepository;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class NegotiationServiceImpl extends ErroMessage implements NegotiationService {

    @Autowired
    private NegotiationRepository repository;

    @Autowired
    private NegotiationModelAssembler negotiationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<NegotiationEntity> assembler;

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
    public NegotiationModel save(NegotiationEntity negociacao) {
        log.info("NegotiationService - save");
        return negotiationModelAssembler.toModel(repository.save(negociacao));
    }

    @Override
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
    public NegotiationModel deletar(Long id) {
        log.info("NegotiationService - deletar");
        NegotiationEntity negotiationEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        repository.deleteById(id);
        return negotiationModelAssembler.toModel(negotiationEntity);
    }

    /**
     * TODO: Criar formula para calcular os valores da negociação.
     */
    @Override
    public void calculo(Long id) {
        NegotiationEntity negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        BigDecimal somaTotal = negociacao.getNegociacaoProdutos().stream().map(total -> total.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal somaMensal = negociacao.getNegociacaoProdutos().stream().filter(mensal -> mensal.getReincidencia().equals(RecidivismType.RECORRENTE)).map(total -> total.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal somaUnico = negociacao.getNegociacaoProdutos().stream().filter(mensal -> mensal.getReincidencia().equals(RecidivismType.UNICO)).map(unico -> unico.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        negociacao.setValorMensal(somaMensal);
        negociacao.setValorUnico(somaUnico);
        negociacao.setValorTotal(somaTotal);
        repository.save(negociacao);
    }

    @Override
    public void atribuirPropMP(Long id, ReasonForLossEntity motivoPerda) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        negociacaoSalva.setMotivoPerda(motivoPerda);
        negociacaoSalva.setEstatus(StatusNegotiation.PERDIDA);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeEstatus(Long id, StatusNegotiation estatus) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        if (negociacaoSalva.getEstatus() == StatusNegotiation.PERDIDA)
            negociacaoSalva.setMotivoPerda(null);
        negociacaoSalva.setEstatus(estatus);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeDataFim(Long id, LocalDateTime data) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        negociacaoSalva.setDataPrevistaEncerramento(data);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeEstagio(Long id, NegotiationStyleEntity estagio) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        negociacaoSalva.setEstagio(estagio);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "[negotiation]"));
        negociacaoSalva.setAvaliacao(avaliacao);
        repository.save(negociacaoSalva);
    }
}
