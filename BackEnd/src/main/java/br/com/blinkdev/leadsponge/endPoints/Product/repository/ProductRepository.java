package br.com.blinkdev.leadsponge.endPoints.Product.repository;

import br.com.blinkdev.leadsponge.endPoints.Product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryQuery {

}
