package br.com.blinkdev.leadsponge.end_points.negotiation_source.service;

import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.filter.NegotiationSourceFilter;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.model.NegotiationSourceModel;
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
