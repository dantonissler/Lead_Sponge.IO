package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.repository;

import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonForLossRepository extends JpaRepository<ReasonForLossEntity, Long>, ReasonForLossRepositoryQuery {

}
