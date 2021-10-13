package br.com.blinkdev.leadsponge.end_points.negotiation_source.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationSourceRepository extends JpaRepository<NegotiationSourceEntity, Long>, NegotiationSourceRepositoryQuery {

}
