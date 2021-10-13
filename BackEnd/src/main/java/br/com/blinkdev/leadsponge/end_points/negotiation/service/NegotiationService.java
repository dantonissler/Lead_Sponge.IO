package br.com.blinkdev.leadsponge.end_points.negotiation.service;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.end_points.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.end_points.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
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

    // TODO: estudar se é necessario manter esses métodos pois existe o método patch

    void atualizarPropriedadeDataFim(Long id, LocalDateTime data);

    void updateNegotiationStyle(Long id, NegotiationStyleEntity estagio);

    void atualizarPropriedadeAvaliacao(Long id, Integer avaliacao);
}
