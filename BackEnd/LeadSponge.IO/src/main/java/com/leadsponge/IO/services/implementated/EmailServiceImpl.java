package com.leadsponge.IO.services.implementated;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.repository.Filter.EmailFilter;
import com.leadsponge.IO.repository.email.EmailRepository;
import com.leadsponge.IO.services.EmailService;

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
