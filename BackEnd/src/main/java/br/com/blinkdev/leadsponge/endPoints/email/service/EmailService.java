package br.com.blinkdev.leadsponge.endPoints.email.service;

import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.endPoints.email.filter.EmailFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	EmailEntity salvar(EmailEntity contato);

	EmailEntity atualizar(Long id, EmailEntity contato);

	EmailEntity deletar(Long id);

	EmailEntity detalhar(Long id);

	Page<EmailEntity> filtrar(EmailFilter clienteFilter, Pageable pageable);
}
