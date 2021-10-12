package br.com.blinkdev.leadsponge.endpoints.negotiationStyle.repository;

import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationStyleRepository extends JpaRepository<NegotiationStyleEntity, Long>, NegotiationStyleRepositoryQuery {

}
