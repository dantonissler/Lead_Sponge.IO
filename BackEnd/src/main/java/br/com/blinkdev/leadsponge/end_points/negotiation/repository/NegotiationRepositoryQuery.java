package br.com.blinkdev.leadsponge.end_points.negotiation.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.filter.NegotiationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationRepositoryQuery {
    Page<NegotiationEntity> filtrar(NegotiationFilter negociacaoFilter, Pageable pageable);
}
