package br.com.blinkdev.leadsponge.endpoints.customer.repository;

import br.com.blinkdev.leadsponge.endpoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endpoints.customer.filter.CustomerFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositoryQuery {
    Page<CustomerEntity> searchWithFilters(CustomerFilter lancamentoFilter, Pageable pageable);
}
