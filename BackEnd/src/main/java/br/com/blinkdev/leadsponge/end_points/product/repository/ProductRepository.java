package br.com.blinkdev.leadsponge.end_points.product.repository;

import br.com.blinkdev.leadsponge.end_points.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryQuery {

}
