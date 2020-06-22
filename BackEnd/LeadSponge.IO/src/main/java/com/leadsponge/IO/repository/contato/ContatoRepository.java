package com.leadsponge.IO.repository.contato;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.contato.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>, ContatoRepositoryQuery {
	Iterable<Contato> findByClienteContato(Cliente cliente);
}
