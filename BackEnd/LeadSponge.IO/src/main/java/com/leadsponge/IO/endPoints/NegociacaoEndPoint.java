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
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.repository.NegociacaoRepository;

@RestController
@RequestMapping("/negociacoes")
class NegociacaoEndPoint extends CrudController {

	@Autowired
	private final NegociacaoRepository repository;
	
	@Autowired
	private final ApplicationEventPublisher publisher;
	
	public NegociacaoEndPoint(NegociacaoRepository repository, ApplicationEventPublisher publisher) {
		 this.publisher = publisher;
		 this.repository = repository; 
	}
	
	@GetMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
	public ResponseEntity<Iterable<?>> listar() {
		Iterable<Negociacao> negociacao = repository.findAll();
		if (negociacao == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(negociacao);
		}
	}

//	@PostMapping(value = { "", "/" })
//	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
//	ResponseEntity<Negociacao> cadastrar(@Valid @RequestBody Negociacao negociacao, HttpServletResponse response) {
//		Negociacao criarnegociacao = repository.save(negociacao);
//		if (criarnegociacao == null) {
//			return ResponseEntity.notFound().build();
//		} else {
//			publisher.publishEvent(new RecursoCriadoEvent(this, response, criarnegociacao.getId()));
//			return ResponseEntity.status(HttpStatus.CREATED).body(criarnegociacao);
//		}
//	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
	public ResponseEntity<Negociacao> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociacao")));
	}

//	@PutMapping(value = { "/{id}", "/{id}/" })
//	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
//	Negociacao editar(@RequestBody Negociacao novonegociacao, @PathVariable Long id) {
//		return repository.findById(id).map(negociacao -> {
//			negociacao.setNome(novonegociacao.getNome());
//			return repository.save(negociacao);
//		}).orElseThrow(() -> notFouldId(id, "a negociacao"));
//	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_NEGOCIACAO') and #oauth2.hasScope('write')")
	public ResponseEntity<Negociacao> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw notFouldId(id, "a negociacao");
		}
	}
}
