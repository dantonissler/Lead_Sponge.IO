package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.repository.Filter.EmailFilter;

@Service
public interface EmailService {
	Email salvar(Email contato);

	Email atualizar(Long id, Email contato);

	Email deletar(Long id);

	Email detalhar(Long id);

	Page<Email> filtrar(EmailFilter clienteFilter, Pageable pageable);
}
