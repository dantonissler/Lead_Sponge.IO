package com.leadsponge.IO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.contato.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	Iterable<Contato> findByClienteContato(Cliente cliente);
}
