package com.leadsponge.IO.endPoints;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.endPoints.crudEndpoints.CrudController;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.Cliente;
import com.leadsponge.IO.models.Contato;
import com.leadsponge.IO.repository.ContatoRepository;

@RestController
@CrossOrigin
@RequestMapping("/contatos")
class ContatoEndPoint extends CrudController {

	@Autowired
	private final ContatoRepository repository;
	
	@Autowired
	private final ApplicationEventPublisher publisher;
	
	public ContatoEndPoint(ContatoRepository repository, ApplicationEventPublisher publisher) {
		this.publisher = publisher;
		this.repository = repository;
	}

	@GetMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
	public ResponseEntity<Iterable<?>> listar() {
		Iterable<Contato> clientes = repository.findAll();
		if (clientes == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(clientes);
		}
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
	ResponseEntity<Contato> cadastrar(@Valid @RequestBody Contato novoContato , HttpServletResponse response) {
		Contato criarCliente = repository.save(novoContato);
		if (criarCliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
	public ResponseEntity<Contato> detalhar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o contato")));
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
	ResponseEntity<Contato> editar(@Valid @RequestBody Contato novoContato, @PathVariable Long id)
			throws URISyntaxException {
		return ResponseEntity.ok(repository.findById(id).map(contato -> {
			contato.setNome(novoContato.getNome());
			contato.setCargo(novoContato.getCargo());
			contato.setCliente(novoContato.getCliente());
			return repository.save(contato);
		}).orElseThrow(() -> notFouldId(id, "o contato")));
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CONTATO') and #oauth2.hasScope('write')")
	public ResponseEntity<Cliente> remover(@PathVariable Long id){
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw notFouldId(id, "o contato");
		}
	}
}
