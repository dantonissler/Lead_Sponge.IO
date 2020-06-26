package com.leadsponge.IO.endPoints;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.endPoints.crudEndpoints.CrudController;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.repository.Filter.ClienteFilter;
import com.leadsponge.IO.repository.cliente.ClienteRepository;
import com.leadsponge.IO.services.ClienteService;

@RestController
@RequestMapping("/clientes")
class ClienteEndPoint extends CrudController {

	@Autowired
	private final ClienteRepository repository;

	@Autowired
	private final ClienteService clienteService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	ClienteEndPoint(ClienteRepository repository, ApplicationEventPublisher publisher,
			ClienteService clienteService) {
		this.repository = repository;
		this.publisher = publisher;
		this.clienteService = clienteService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public Page<Cliente> pesquisar(ClienteFilter clienteFilter, Pageable pageable) {
		return repository.filtrar(clienteFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente criarCliente = clienteService.save(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id,
			HttpServletResponse response) {
		try {
			Cliente novoCliente = clienteService.atualizar(id, cliente);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novoCliente.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "o cliente");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<Cliente> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "o cliente");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
	public ResponseEntity<Cliente> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente")));
	}
}
