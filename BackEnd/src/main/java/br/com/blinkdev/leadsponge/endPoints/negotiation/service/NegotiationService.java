package br.com.blinkdev.leadsponge.endPoints.negotiation.service;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endPoints.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface NegotiationService {
    void calculo(Long id);

    void atribuirPropMP(Long id, ReasonForLossEntity motivoPerda);

    void atualizarPropriedadeEstatus(Long id, StatusNegotiation estatus);

    void atualizarPropriedadeDataFim(Long id, LocalDateTime data);

    void atualizarPropriedadeEstagio(Long id, NegotiationStyleEntity estagio);

    void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao);

    NegotiationEntity salvar(NegotiationEntity negociacao);

    NegotiationEntity atualizar(Long id, NegotiationEntity negociacao);

    NegotiationEntity deletar(Long id);

    NegotiationEntity detalhar(Long id);

    Page<NegotiationEntity> filtrar(NegotiationFilter negociacaoFilter, Pageable pageable);

}
