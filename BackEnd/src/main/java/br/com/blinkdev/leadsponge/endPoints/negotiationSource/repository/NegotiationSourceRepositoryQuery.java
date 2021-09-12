package br.com.blinkdev.leadsponge.endPoints.negotiationSource.repository;

import br.com.blinkdev.leadsponge.endPoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.filter.NegotiationSourceFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationSourceRepositoryQuery {
    Page<NegotiationSourceEntity> filtrar(NegotiationSourceFilter fonteNegociacaoFilter, Pageable pageable);

}
