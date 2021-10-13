package br.com.blinkdev.leadsponge.end_points.product.service;

import br.com.blinkdev.leadsponge.end_points.product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.end_points.product.filter.ProductFilter;
import br.com.blinkdev.leadsponge.end_points.product.model.ProductModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ProductService {

    ProductModel getById(Long id);

    PagedModel<ProductModel> searchWithFilters(ProductFilter productFilter, Pageable pageable);

    ProductModel save(ProductEntity productEntity);

    ProductModel patch(Long id, Map<Object, Object> fields);

    ProductModel delete(Long id);

    void atualizarPropriedadeVisibilidade(Long id, Boolean visibilidade);
}
