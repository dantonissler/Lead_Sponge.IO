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
import com.leadsponge.IO.models.telefone.Telefone;
import com.leadsponge.IO.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefones")
class TelefoneEndPoint extends CrudController {
	@Autowired
	private final TelefoneRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	TelefoneEndPoint(TelefoneRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Iterable<?>> listar() {
		Iterable<Telefone> telefone = repository.findAll();
		if (telefone == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(telefone);
		}
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<Telefone> cadastrar(@Valid @RequestBody Telefone telefone, HttpServletResponse response) {
		Telefone criartelefone = repository.save(telefone);
		if (criartelefone == null) {
			return ResponseEntity.notFound().build();
		} else {
			publisher.publishEvent(new RecursoCriadoEvent(this, response, criartelefone.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(criartelefone);
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Telefone> detalhar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o telefone")));
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Telefone> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw notFouldId(id, "o telefone");
		}
	}
}
