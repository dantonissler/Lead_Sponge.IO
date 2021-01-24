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

import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;
import com.leadsponge.IO.services.EstagioNegociacaoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/estagios")
class EstagioNegociacaoEndPoint {

	@Autowired
	private final EstagioNegociacaoService service;

	@Autowired
	private final ApplicationEventPublisher publisher;

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
	Page<EstagioNegociacao> pesquisar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable) {
		return service.filtrar(estagioNegociacaoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
	ResponseEntity<EstagioNegociacao> cadastrar(@Valid @RequestBody EstagioNegociacao estagioNegociacao, HttpServletResponse response) {
		EstagioNegociacao criarEstagioNegociacao = service.salvar(estagioNegociacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarEstagioNegociacao.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarEstagioNegociacao);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
	ResponseEntity<EstagioNegociacao> atualizar(@Valid @RequestBody EstagioNegociacao estagioNegociacao, @PathVariable Long id, HttpServletResponse response) {
		EstagioNegociacao novaEstagioNegociacao = service.atualizar(id, estagioNegociacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, novaEstagioNegociacao.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(novaEstagioNegociacao);
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_ESTAGIO') and #oauth2.hasScope('write')")
	ResponseEntity<EstagioNegociacao> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletar(id));
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
	public ResponseEntity<EstagioNegociacao> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(service.detalhar(id));
	}
}
