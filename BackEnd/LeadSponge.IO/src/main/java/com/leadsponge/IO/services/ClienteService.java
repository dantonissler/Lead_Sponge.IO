package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.cliente.Cliente;

@Service
public interface ClienteService {
	public Cliente salvar(Cliente cliente);

	public Cliente atualizar(Long id, Cliente cliente);

}
