package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.cliente.ClienteRepository;
import com.leadsponge.IO.repository.segmento.SegmentoRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private SegmentoRepository segmentoRepository;
	
	public Cliente save(Cliente cliente) {
		clienteValidar(cliente);
		return clienteRepository.save(cliente);
	}
	
	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalvo = buscarClienteExistente(id);
		
		if (!cliente.getSegmento().equals(clienteSalvo.getSegmento())) {
			validarPessoa(cliente);
		}
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
	
	private void validarPessoa(Cliente cliente) {
		Segmento segmento = null;
		if (cliente.getSegmento().getId() != null) {
			segmento = segmentoRepository.getOne(cliente.getSegmento().getId());
		}

		if (segmento == null) {
			throw new UsuarioInativaException();
		}
	}
	
	private void clienteValidar(Cliente Cliente) {
		if (Cliente == null) {
			throw new UsuarioInativaException();
		}
	}
}
