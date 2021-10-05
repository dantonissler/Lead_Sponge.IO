package br.com.blinkdev.leadsponge.relationship.tradeProducts.repository;

import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeProductsRepository extends JpaRepository<TradeProductsEntity, Long>, TradeProductsRepositoryQuery {

}
