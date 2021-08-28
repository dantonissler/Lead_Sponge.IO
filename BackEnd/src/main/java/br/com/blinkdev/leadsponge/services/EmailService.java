package br.com.blinkdev.leadsponge.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.models.email.Email;
import br.com.blinkdev.leadsponge.repository.Filter.EmailFilter;

@Service
public interface EmailService {
	Email salvar(Email contato);

	Email atualizar(Long id, Email contato);

	Email deletar(Long id);

	Email detalhar(Long id);

	Page<Email> filtrar(EmailFilter clienteFilter, Pageable pageable);
}
