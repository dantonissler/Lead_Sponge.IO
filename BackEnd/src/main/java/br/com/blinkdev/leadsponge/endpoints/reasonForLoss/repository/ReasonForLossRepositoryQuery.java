package br.com.blinkdev.leadsponge.endpoints.reasonForLoss.repository;

import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.filter.ReasonForLossFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonForLossRepositoryQuery {
    Page<ReasonForLossEntity> filtrar(ReasonForLossFilter motivoPerdaFilter, Pageable pageable);
}
