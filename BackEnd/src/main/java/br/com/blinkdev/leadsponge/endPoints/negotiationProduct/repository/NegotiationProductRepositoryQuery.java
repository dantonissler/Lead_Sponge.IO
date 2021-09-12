package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.repository;

import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter.NegotiationProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationProductRepositoryQuery {
    Page<NegotiationProductEntity> filtrar(NegotiationProductFilter negociacaoFilter, Pageable pageable);
}
