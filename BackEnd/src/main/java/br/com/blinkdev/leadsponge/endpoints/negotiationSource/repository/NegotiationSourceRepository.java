package br.com.blinkdev.leadsponge.endpoints.negotiationSource.repository;

import br.com.blinkdev.leadsponge.endpoints.negotiationSource.entity.NegotiationSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationSourceRepository extends JpaRepository<NegotiationSourceEntity, Long>, NegotiationSourceRepositoryQuery {

}
