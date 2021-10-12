package br.com.blinkdev.leadsponge.endpoints.customer.service;

import br.com.blinkdev.leadsponge.endpoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endpoints.customer.filter.CustomerFilter;
import br.com.blinkdev.leadsponge.endpoints.customer.model.CustomerModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CustomerService {

    CustomerModel getById(Long id);

    PagedModel<CustomerModel> searchWithFilters(CustomerFilter clienteFilter, Pageable pageable);

    CustomerModel save(CustomerEntity customer);

    CustomerModel patch(Long id, Map<Object, Object> fields);

    CustomerModel delete(Long id);

}

