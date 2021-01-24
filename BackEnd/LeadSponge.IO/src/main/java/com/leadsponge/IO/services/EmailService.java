package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.repository.Filter.EmailFilter;

@Service
public interface EmailService {
	public Email salvar(Email contato);

	public Email atualizar(Long id, Email contato);

	public Email deletar(Long id);

	public Email detalhar(Long id);

	public Page<Email> filtrar(EmailFilter clienteFilter, Pageable pageable);
}
