package br.com.blinkdev.leadsponge.repository.email;

import br.com.blinkdev.leadsponge.repository.Filter.EmailFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.blinkdev.leadsponge.models.email.Email;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepositoryQuery {
	Page<Email> filtrar(EmailFilter emailFilter, Pageable pageable);
}
