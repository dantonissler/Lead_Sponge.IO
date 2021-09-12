package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.repository;

import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationProductRepository extends JpaRepository<NegotiationProductEntity, Long>, NegotiationProductRepositoryQuery {

}
