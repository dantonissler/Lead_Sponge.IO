package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.ClienteFilter;

@Service
public interface ClienteService {
	Cliente salvar(Cliente cliente);

	Cliente atualizar(Long id, Cliente cliente);

	Cliente deletar(Long id);

	Cliente detalhar(Long id);

	Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
