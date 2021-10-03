package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.service;

import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter.NegotiationProductFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.model.NegotiationProductModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface NegotiationProductService {

    NegotiationProductModel getById(Long id);

    PagedModel<NegotiationProductModel> searchWithFilters(NegotiationProductFilter negociacaoProdutoFilter, Pageable pageable);

    NegotiationProductModel save(NegotiationProductEntity nProduto);

    NegotiationProductModel patch(Long id,  Map<Object, Object> fields);

    NegotiationProductModel delete(Long id);
}
