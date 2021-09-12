package br.com.blinkdev.leadsponge.endPoints.customer.service;

import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.filter.CustomerFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerEntity salvar(CustomerEntity cliente);

    CustomerEntity atualizar(Long id, CustomerEntity cliente);

    CustomerEntity deletar(Long id);

    CustomerEntity detalhar(Long id);

    Page<CustomerEntity> filtrar(CustomerFilter clienteFilter, Pageable pageable);

}
