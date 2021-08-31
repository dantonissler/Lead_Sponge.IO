package br.com.blinkdev.leadsponge.services.email;

import br.com.blinkdev.leadsponge.models.email.Email;
import br.com.blinkdev.leadsponge.models.email.EmailFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	Email salvar(Email contato);

	Email atualizar(Long id, Email contato);

	Email deletar(Long id);

	Email detalhar(Long id);

	Page<Email> filtrar(EmailFilter clienteFilter, Pageable pageable);
}
