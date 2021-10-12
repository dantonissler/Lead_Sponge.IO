package br.com.blinkdev.leadsponge.endpoints.negotiation.service;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endpoints.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.endpoints.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.entity.ReasonForLossEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public interface NegotiationService {

    NegotiationModel getById(Long id);

    PagedModel<NegotiationModel> searchWithFilters(NegotiationFilter negociacaoFilter, Pageable pageable);

    NegotiationModel save(NegotiationEntity negociacao);

    NegotiationModel patch(Long id, Map<Object, Object> fields);

    NegotiationModel deletar(Long id);

    void calculateValues(Long id);

    void updateNegotiationRFLE(Long id, ReasonForLossEntity motivoPerda);

    void updateNegotiationStatus(Long id, StatusNegotiation estatus);

    void atualizarPropriedadeDataFim(Long id, LocalDateTime data);

    void updateNegotiationStyle(Long id, NegotiationStyleEntity estagio);

    void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao);
}
