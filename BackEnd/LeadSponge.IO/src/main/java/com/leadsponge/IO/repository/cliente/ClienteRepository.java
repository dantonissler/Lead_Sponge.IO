package com.leadsponge.IO.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
