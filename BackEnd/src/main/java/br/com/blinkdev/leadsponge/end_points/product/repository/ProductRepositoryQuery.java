package br.com.blinkdev.leadsponge.end_points.product.repository;

import br.com.blinkdev.leadsponge.end_points.product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.end_points.product.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryQuery {
    Page<ProductEntity> filtrar(ProductFilter produtoFilter, Pageable pageable);
}
