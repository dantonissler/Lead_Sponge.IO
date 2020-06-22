package com.leadsponge.IO.endPoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.endPoints.crudEndpoints.CrudController;

@RestController
@RequestMapping("/clientes")
class ClienteEndPoint extends CrudController {

	/* TODO
	 * Criar o cliente com muitos ou nem um emails e telefones
	 */
	
//	@Autowired
//	private final ClienteRepository repository;
//	
//	@Autowired
//	private ApplicationEventPublisher publisher;
//
//	ClienteEndPoint(ClienteRepository repository, ApplicationEventPublisher publisher) {
//		this.repository = repository;
//		this.publisher = publisher;
//	}
//
//	@GetMapping(value = { "", "/" })
//	@PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
//	public ResponseEntity<Iterable<?>> listar() {
//		Iterable<Cliente> clientes = repository.findAll();
//		if (clientes == null) {
//			return ResponseEntity.notFound().build();
//		} else {
//			return ResponseEntity.ok(clientes);
//		}
//	}
//
//	@PostMapping(value = { "", "/" })
//	@PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
//	ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
//		Cliente criarCliente = repository.save(cliente);
//		if (criarCliente == null) {
//			return ResponseEntity.notFound().build();
//		} else {
//			publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
//			return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
//		}
//	}
//
//	@GetMapping(value = { "/{id}", "/{id}/" })
//	@PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
//	public ResponseEntity<Cliente> detalhar(@PathVariable("id") Long id) {
//		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente")));
//	}
//
//	@PutMapping(value = { "/{id}", "/{id}/" })
//	@PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
//	ResponseEntity<Cliente> editar(@Valid @RequestBody Cliente novoCliente, @PathVariable Long id) {
//		return ResponseEntity.ok(repository.findById(id).map(cliente -> {
//			cliente.setNome(novoCliente.getNome());
//			cliente.setResumo(novoCliente.getResumo());
//			cliente.setUrl(novoCliente.getUrl());
//			return repository.save(cliente);
//		}).orElseThrow(() -> notFouldId(id, "o cliente")));
//	}
//
//	@DeleteMapping(value = { "/{id}", "/{id}/" })
//	@PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
//	public ResponseEntity<Cliente> remover(@PathVariable Long id) {
//		try {
//			repository.deleteById(id);
//			return ResponseEntity.noContent().build();
//		} catch (Exception e) {
//			throw notFouldId(id, "o cliente");
//		}
//	}
}
