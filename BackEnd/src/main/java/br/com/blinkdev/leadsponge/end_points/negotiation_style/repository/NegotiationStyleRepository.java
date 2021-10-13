package br.com.blinkdev.leadsponge.end_points.negotiation_style.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationStyleRepository extends JpaRepository<NegotiationStyleEntity, Long>, NegotiationStyleRepositoryQuery {

}
