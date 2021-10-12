package br.com.blinkdev.leadsponge.endpoints.Product.repository;

import br.com.blinkdev.leadsponge.endpoints.Product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryQuery {

}
