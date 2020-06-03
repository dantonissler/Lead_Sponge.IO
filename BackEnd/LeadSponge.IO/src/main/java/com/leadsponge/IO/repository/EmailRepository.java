package com.leadsponge.IO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.Contato;
import com.leadsponge.IO.models.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
	Iterable<Email> findByContato(Contato contato);
}
