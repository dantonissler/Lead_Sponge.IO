package br.com.blinkdev.leadsponge.services.email;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.models.email.Email;
import br.com.blinkdev.leadsponge.models.email.EmailFilter;
import br.com.blinkdev.leadsponge.repository.email.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl extends ErroMessage implements EmailService {

	@Autowired
	private EmailRepository repository;

	@Override
	public Page<Email> filtrar(EmailFilter emailFilter, Pageable pageable) {
		return repository.filtrar(emailFilter, pageable);
	}

	@Override
	public Email salvar(Email email) {
		return repository.save(email);
	}

	@Override
	public Email atualizar(Long id, Email email) {
		Email emailSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a email"));
		BeanUtils.copyProperties(email, emailSalvo, "id");
		return repository.save(emailSalvo);
	}

	@Override
	public Email deletar(Long id) {
		Email emailSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a email"));
		repository.deleteById(id);
		return emailSalvo;
	}

	@Override
	public Email detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a email"));
	}
}
