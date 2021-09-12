package br.com.blinkdev.leadsponge.endPoints.customer.repository;

import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryQuery {
}
