package br.com.blinkdev.leadsponge.relationship.tradeProducts.repository;


import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.filter.TradeProductsFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeProductsRepositoryQuery {
    Page<TradeProductsEntity> filtrar(TradeProductsFilter negociacaoFilter, Pageable pageable);
}
