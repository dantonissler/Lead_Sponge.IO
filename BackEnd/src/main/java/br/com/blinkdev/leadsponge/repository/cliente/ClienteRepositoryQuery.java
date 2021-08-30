package br.com.blinkdev.leadsponge.repository.cliente;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.cliente.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositoryQuery {
	Page<Cliente> filtrar(ClienteFilter lancamentoFilter, Pageable pageable);
}
