package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.repository.Filter.ClienteFilter;

@Service
public interface ClienteService {
	public Cliente salvar(Cliente cliente);

	public Cliente atualizar(Long id, Cliente cliente);

	public Cliente deletar(Long id);

	public Cliente detalhar(Long id);

	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
