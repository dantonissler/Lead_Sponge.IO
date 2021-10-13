package br.com.blinkdev.leadsponge.end_points.customer.repository;

import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryQuery {
}
