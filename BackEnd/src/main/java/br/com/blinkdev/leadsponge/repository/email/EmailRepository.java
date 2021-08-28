package br.com.blinkdev.leadsponge.repository.email;

import br.com.blinkdev.leadsponge.models.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blinkdev.leadsponge.models.email.Email;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>, EmailRepositoryQuery {
	Iterable<Email> findByContatoEmail(Contato contato);
}
