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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.endPoints.crudEndpoints.CrudController;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.Cliente;
import com.leadsponge.IO.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteEndPoints extends CrudController {

	/* TODO
	 * Criar o cliente com muitos ou nem um emails e telefones
	 */
	
	@Autowired
	private final ClienteRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	ClienteEndPoints(ClienteRepository repository, ApplicationEventPublisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	@GetMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Iterable<?>> listar() {
		Iterable<Cliente> clientes = repository.findAll();
		if (clientes == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(clientes);
		}
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente criarCliente = repository.save(cliente);
		if (criarCliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Cliente> detalhar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente")));
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<Cliente> editar(@Valid @RequestBody Cliente novoCliente, @PathVariable Long id) {
		return ResponseEntity.ok(repository.findById(id).map(cliente -> {
			cliente.setNome(novoCliente.getNome());
			cliente.setResumo(novoCliente.getResumo());
			cliente.setUrl(novoCliente.getUrl());
			cliente.setSegmento(novoCliente.getSegmento());
			return repository.save(cliente);
		}).orElseThrow(() -> notFouldId(id, "o cliente")));
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Cliente> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw notFouldId(id, "o cliente");
		}
	}
}
