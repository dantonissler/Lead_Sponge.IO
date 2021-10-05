package br.com.blinkdev.leadsponge.relationship.tradeProducts.service;

import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.filter.TradeProductsFilter;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.model.TradeProductsModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TradeProductsService {

    TradeProductsModel getById(Long id);

    PagedModel<TradeProductsModel> searchWithFilters(TradeProductsFilter negociacaoProdutoFilter, Pageable pageable);

    TradeProductsModel save(TradeProductsEntity nProduto);

    TradeProductsModel patch(Long id, Map<Object, Object> fields);

    TradeProductsModel delete(Long id);
}
