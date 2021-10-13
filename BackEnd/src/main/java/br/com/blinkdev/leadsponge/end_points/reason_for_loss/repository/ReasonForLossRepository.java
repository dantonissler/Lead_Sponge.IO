package br.com.blinkdev.leadsponge.end_points.reason_for_loss.repository;

import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonForLossRepository extends JpaRepository<ReasonForLossEntity, Long>, ReasonForLossRepositoryQuery {

}
