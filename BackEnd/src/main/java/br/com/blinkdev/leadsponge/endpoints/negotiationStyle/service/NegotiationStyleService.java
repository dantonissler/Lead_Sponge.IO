package br.com.blinkdev.leadsponge.endpoints.negotiationStyle.service;

import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.filter.NegotiationStyleFilter;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.model.NegotiationStyleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface NegotiationStyleService {

    NegotiationStyleModel getById(Long id);

    PagedModel<NegotiationStyleModel> searchWithFilters(NegotiationStyleFilter estagioNegociacaoFilter, Pageable pageable);

    NegotiationStyleModel save(NegotiationStyleEntity estagioNegociacao);

    NegotiationStyleModel patch(Long id, Map<Object, Object> fields);

    NegotiationStyleModel delete(Long id);
}
