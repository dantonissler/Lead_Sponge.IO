package br.com.blinkdev.leadsponge.end_points.negotiation_source.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.filter.NegotiationSourceFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationSourceRepositoryQuery {
    Page<NegotiationSourceEntity> filtrar(NegotiationSourceFilter fonteNegociacaoFilter, Pageable pageable);

}
