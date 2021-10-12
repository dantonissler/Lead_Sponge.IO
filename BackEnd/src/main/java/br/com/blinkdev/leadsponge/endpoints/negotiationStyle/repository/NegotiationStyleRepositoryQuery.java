package br.com.blinkdev.leadsponge.endpoints.negotiationStyle.repository;

import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.filter.NegotiationStyleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationStyleRepositoryQuery {
    Page<NegotiationStyleEntity> filtrar(NegotiationStyleFilter estagioNegociacaoFilter, Pageable pageable);
}
