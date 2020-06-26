package com.leadsponge.IO.repository.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.repository.Filter.ClienteFilter;

@Repository
public interface ClienteRepositoryQuery {
	public Page<Cliente> filtrar(ClienteFilter lancamentoFilter, Pageable pageable);
}
