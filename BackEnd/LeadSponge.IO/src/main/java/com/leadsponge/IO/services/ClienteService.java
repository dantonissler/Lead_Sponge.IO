package com.leadsponge.IO.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.repository.cliente.ClienteRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente save(Cliente cliente) {
		clienteValidar(cliente);
		cliente.getContato().forEach(c -> c.setCliente(cliente));
		cliente.getContato().forEach(contato -> contato.getTelefone()
				.forEach(telefone -> telefone.setContato(contato)));
		cliente.getContato().forEach(contato -> contato.getEmail()
				.forEach(email -> email.setContato(contato)));
		cliente.setSegmentos(new ArrayList<>(cliente.getSegmentos()));
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalvo = buscarClienteExistente(id);
		clienteSalvo.getSegmentos().clear();
		clienteSalvo.getSegmentos().addAll(cliente.getSegmentos());
		clienteSalvo.setSegmentos(new ArrayList<>(clienteSalvo.getSegmentos()));
		BeanUtils.copyProperties(cliente, clienteSalvo, "id", "roles");
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return clienteRepository.save(clienteSalvo);
	}

	private Cliente buscarClienteExistente(Long id) {
		Optional<Cliente> clienteSalvo = clienteRepository.findById(id);
		if (!clienteSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return clienteSalvo.get();
	}

	private void clienteValidar(Cliente Cliente) {
		if (Cliente == null) {
			throw new UsuarioInativaException();
		}
	}
}
