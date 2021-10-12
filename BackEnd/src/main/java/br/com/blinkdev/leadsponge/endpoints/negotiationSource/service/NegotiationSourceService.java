package br.com.blinkdev.leadsponge.endpoints.negotiationSource.service;

import br.com.blinkdev.leadsponge.endpoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationSource.filter.NegotiationSourceFilter;
import br.com.blinkdev.leadsponge.endpoints.negotiationSource.model.NegotiationSourceModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface NegotiationSourceService {

    NegotiationSourceModel getById(Long id);

    PagedModel<NegotiationSourceModel> searchWithFilters(NegotiationSourceFilter negotiationSourceFilter, Pageable pageable);

    NegotiationSourceModel save(NegotiationSourceEntity negotiationSourceEntity);

    NegotiationSourceModel patch(Long id, Map<Object, Object> fields);

    NegotiationSourceModel delete(Long id);
}
