package br.com.blinkdev.leadsponge.endPoints.negotiation.service;

import br.com.blinkdev.leadsponge.endPoints.customer.repository.CustomerRepository;
import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.RecidivismType;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endPoints.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiation.repository.NegotiationRepository;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class NegotiationServiceImpl extends ErroMessage implements NegotiationService {

    @Autowired
    private NegotiationRepository repository;

    @Autowired
    private CustomerRepository clienteR;

    /**
     * TODO: Criar formula para calcular os valores da negociação.
     */
    @Override
    public void calculo(Long id) {
        NegotiationEntity negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
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
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
        negociacaoSalva.setMotivoPerda(motivoPerda);
        negociacaoSalva.setEstatus(StatusNegotiation.PERDIDA);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeEstatus(Long id, StatusNegotiation estatus) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
        if (negociacaoSalva.getEstatus() == StatusNegotiation.PERDIDA)
            negociacaoSalva.setMotivoPerda(null);
        negociacaoSalva.setEstatus(estatus);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeDataFim(Long id, LocalDateTime data) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
        negociacaoSalva.setDataPrevistaEncerramento(data);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeEstagio(Long id, NegotiationStyleEntity estagio) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
        negociacaoSalva.setEstagio(estagio);
        repository.save(negociacaoSalva);
    }

    @Override
    public void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao) {
        NegotiationEntity negociacaoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
        negociacaoSalva.setAvaliacao(avaliacao);
        repository.save(negociacaoSalva);
    }

    @Override
    public NegotiationEntity salvar(NegotiationEntity negociacao) {
        return repository.save(negociacao);
    }

    @Override
    public NegotiationEntity atualizar(Long id, NegotiationEntity negociacao) {
        NegotiationEntity novaNegociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação "));
        clienteR.findById(negociacao.getCliente().getId()).orElseThrow(() -> notFouldId(negociacao.getCliente().getId(), "o cliente"));
        // TODO fazer as devidas validações
        BeanUtils.copyProperties(negociacao, novaNegociacao, "id");
        return repository.save(novaNegociacao);
    }

    @Override
    public NegotiationEntity deletar(Long id) {
        NegotiationEntity negociacao = repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
        repository.deleteById(id);
        return negociacao;
    }

    @Override
    public NegotiationEntity detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte da negociação "));
    }

    @Override
    public Page<NegotiationEntity> filtrar(NegotiationFilter negociacaoFilter, Pageable pageable) {
        // TODO fazer as devidas validações
        return repository.filtrar(negociacaoFilter, pageable);
    }
}
