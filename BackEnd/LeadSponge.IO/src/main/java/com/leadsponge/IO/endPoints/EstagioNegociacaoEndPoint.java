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

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;
import com.leadsponge.IO.repository.estagioNegociacao.EstagioNegociacaoRepository;
import com.leadsponge.IO.services.EstagioNegociacaoService;

@RestController
@RequestMapping("/estagios")
class EstagioNegociacaoEndPoint extends ErroMessage {

	@Autowired
	private final EstagioNegociacaoRepository repository;

	@Autowired
	private final EstagioNegociacaoService estagioNegociacaoService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	EstagioNegociacaoEndPoint(EstagioNegociacaoRepository repository, ApplicationEventPublisher publisher,
			EstagioNegociacaoService estagioNegociacaoService) {
		this.repository = repository;
		this.publisher = publisher;
		this.estagioNegociacaoService = estagioNegociacaoService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
	Page<EstagioNegociacao> pesquisar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable) {
		return repository.filtrar(estagioNegociacaoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
	ResponseEntity<EstagioNegociacao> cadastrar(@Valid @RequestBody EstagioNegociacao estagioNegociacao,
			HttpServletResponse response) {
		EstagioNegociacao criarEstagioNegociacao = estagioNegociacaoService.save(estagioNegociacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarEstagioNegociacao.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarEstagioNegociacao);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
	ResponseEntity<EstagioNegociacao> atualizar(@Valid @RequestBody EstagioNegociacao estagioNegociacao,
			@PathVariable Long id, HttpServletResponse response) {
		try {
			EstagioNegociacao novaEstagioNegociacao = estagioNegociacaoService.atualizar(id, estagioNegociacao);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaEstagioNegociacao.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaEstagioNegociacao);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "a estagio de negociação");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_ESTAGIO') and #oauth2.hasScope('write')")
	ResponseEntity<EstagioNegociacao> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "a estagio de negociação");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
	public ResponseEntity<EstagioNegociacao> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "a estagio de negociação")));
	}
}
