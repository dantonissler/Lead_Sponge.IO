package br.com.blinkdev.leadsponge.repository.cliente;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.blinkdev.leadsponge.repository.Filter.ClienteFilter;

@Repository
public interface ClienteRepositoryQuery {
	Page<Cliente> filtrar(ClienteFilter lancamentoFilter, Pageable pageable);
}
