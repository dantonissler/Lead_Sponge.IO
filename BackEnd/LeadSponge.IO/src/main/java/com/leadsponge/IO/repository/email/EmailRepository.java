package com.leadsponge.IO.repository.email;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.email.Email;

public interface EmailRepository extends JpaRepository<Email, Long>, EmailRepositoryQuery {
	Iterable<Email> findByContatoEmail(Contato contato);
}
