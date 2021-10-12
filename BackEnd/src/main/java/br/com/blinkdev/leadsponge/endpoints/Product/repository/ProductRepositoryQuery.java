package br.com.blinkdev.leadsponge.endpoints.Product.repository;

import br.com.blinkdev.leadsponge.endpoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endpoints.Product.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryQuery {
    Page<ProductEntity> filtrar(ProductFilter produtoFilter, Pageable pageable);
}
