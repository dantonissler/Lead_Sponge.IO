package br.com.blinkdev.leadsponge.endPoints.email.service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.endPoints.email.filter.EmailFilter;
import br.com.blinkdev.leadsponge.endPoints.email.repository.EmailRepository;
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
	public Page<EmailEntity> filtrar(EmailFilter emailFilter, Pageable pageable) {
		return repository.filtrar(emailFilter, pageable);
	}

	@Override
	public EmailEntity salvar(EmailEntity email) {
		return repository.save(email);
	}

	@Override
	public EmailEntity atualizar(Long id, EmailEntity email) {
		EmailEntity emailSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a email"));
		BeanUtils.copyProperties(email, emailSalvo, "id");
		return repository.save(emailSalvo);
	}

	@Override
	public EmailEntity deletar(Long id) {
		EmailEntity emailSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a email"));
		repository.deleteById(id);
		return emailSalvo;
	}

	@Override
	public EmailEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a email"));
	}
}
