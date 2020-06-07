package com.leadsponge.IO.endPoints;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.endPoints.crudEndpoints.CrudController;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.Email;
import com.leadsponge.IO.repository.EmailRepository;

@RestController
@RequestMapping("/emails")
class EmailEndPoint extends CrudController {
	
	@Autowired
	private final EmailRepository repository;
	
	@Autowired
	private final ApplicationEventPublisher publisher;

	EmailEndPoint(EmailRepository repository, ApplicationEventPublisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	@GetMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Iterable<?>> listar() {
		Iterable<Email> email = repository.findAll();
		if (email == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(email);
		}
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<Email> cadastrar(@Valid @RequestBody Email email, HttpServletResponse response) {
		Email criarEmail = repository.save(email);
		if (criarEmail == null) {
			return ResponseEntity.notFound().build();
		} else {
			publisher.publishEvent(new RecursoCriadoEvent(this, response, criarEmail.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(criarEmail);
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Email> detalhar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o email")));
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Email> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw notFouldId(id, "o email");
		}
	}
}
