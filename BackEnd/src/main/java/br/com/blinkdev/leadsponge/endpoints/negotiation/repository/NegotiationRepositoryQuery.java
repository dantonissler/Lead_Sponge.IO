package br.com.blinkdev.leadsponge.endpoints.negotiation.repository;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.filter.NegotiationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationRepositoryQuery {
    Page<NegotiationEntity> filtrar(NegotiationFilter negociacaoFilter, Pageable pageable);
}
