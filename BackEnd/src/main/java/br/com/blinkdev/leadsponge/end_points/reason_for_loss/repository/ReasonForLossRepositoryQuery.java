package br.com.blinkdev.leadsponge.end_points.reason_for_loss.repository;

import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.filter.ReasonForLossFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonForLossRepositoryQuery {
    Page<ReasonForLossEntity> filtrar(ReasonForLossFilter motivoPerdaFilter, Pageable pageable);
}
