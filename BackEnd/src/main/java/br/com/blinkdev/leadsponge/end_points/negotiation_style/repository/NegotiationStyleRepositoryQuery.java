package br.com.blinkdev.leadsponge.end_points.negotiation_style.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.filter.NegotiationStyleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationStyleRepositoryQuery {
    Page<NegotiationStyleEntity> filtrar(NegotiationStyleFilter estagioNegociacaoFilter, Pageable pageable);
}
