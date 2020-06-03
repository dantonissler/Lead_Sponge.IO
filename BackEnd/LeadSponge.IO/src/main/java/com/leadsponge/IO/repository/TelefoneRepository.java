package com.leadsponge.IO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.Contato;
import com.leadsponge.IO.models.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	Iterable<Telefone> findByContato(Contato contato);
}
