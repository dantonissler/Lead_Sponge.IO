package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.repository;

import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationStyleRepository extends JpaRepository<NegotiationStyleEntity, Long>, NegotiationStyleRepositoryQuery {

}
