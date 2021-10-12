package br.com.blinkdev.leadsponge.endpoints.negotiation.repository;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationRepository extends JpaRepository<NegotiationEntity, Long>, NegotiationRepositoryQuery {

}
