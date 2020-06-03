package com.leadsponge.IO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
