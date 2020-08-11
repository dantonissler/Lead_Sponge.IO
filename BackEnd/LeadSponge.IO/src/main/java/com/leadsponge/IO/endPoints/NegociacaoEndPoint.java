package com.leadsponge.IO.endPoints;

import java.util.Date;

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
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.models.negociacao.EstatusNegociacao;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.repository.Filter.NegociacaoFilter;
import com.leadsponge.IO.repository.negociacao.NegociacaoRepository;
import com.leadsponge.IO.services.NegociacaoService;

@RestController
@RequestMapping("/negociacoes")
class NegociacaoEndPoint extends CrudController {

	@Autowired
	private final NegociacaoRepository repository;

	@Autowired
	private final NegociacaoService negociacaoService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	public NegociacaoEndPoint(NegociacaoRepository repository, ApplicationEventPublisher publisher,
			NegociacaoService negociacaoService) {
		this.publisher = publisher;
		this.repository = repository;
		this.negociacaoService = negociacaoService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
	Page<Negociacao> pesquisar(NegociacaoFilter negociacaoFilter, Pageable pageable) {
		return repository.filtrar(negociacaoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> cadastrar(@Valid @RequestBody Negociacao negociacao, HttpServletResponse response) {
		Negociacao criarNegociacao = negociacaoService.save(negociacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarNegociacao.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarNegociacao);
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
	ResponseEntity<Negociacao> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "a negociação")));
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> atualizar(@RequestBody Negociacao negociacao, @PathVariable Long id,
			HttpServletResponse response) {
		try {
			Negociacao novonegociacao = negociacaoService.atualizar(id, negociacao);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novonegociacao.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novonegociacao);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "a negociacao");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw notFouldId(id, "a negociacao");
		}
	}

	@PutMapping("/{id}/avaliacao")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Integer avaliacao) {
		negociacaoService.atualizarPropriedadeAvaliacao(id, avaliacao);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}/estagio")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> atualizarPropriedadeEstagio(@PathVariable Long id,
			@RequestBody EstagioNegociacao estagio) {
		negociacaoService.atualizarPropriedadeEstagio(id, estagio);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}/estatus")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> atualizarPropriedadeEstatus(@PathVariable Long id,
			@RequestBody EstatusNegociacao estatus) {
		negociacaoService.atualizarPropriedadeEstatus(id, estatus);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}/dataFim")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> atualizarPropriedadeDataFim(@PathVariable Long id, @RequestBody Date data) {
		negociacaoService.atualizarPropriedadeDataFim(id, data);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}/perda")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<Negociacao> atribuirMotivoPerda(@PathVariable Long id, @RequestBody MotivoPerda morivoPerda) {
		negociacaoService.atribuirPropMP(id, morivoPerda);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
