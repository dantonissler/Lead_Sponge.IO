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
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.repository.Filter.FonteNegociacaoFilter;
import com.leadsponge.IO.repository.fonteNegociacao.FonteNegociacaoRepository;
import com.leadsponge.IO.services.implementated.FonteNegociacaoServiceImpl;

@RestController
@RequestMapping("/fontes")
class FonteNegociacaoEndPoint extends ErroMessage {

	@Autowired
	private final FonteNegociacaoRepository repository;

	@Autowired
	private final FonteNegociacaoServiceImpl fonteNegociacaoService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	FonteNegociacaoEndPoint(FonteNegociacaoRepository repository, ApplicationEventPublisher publisher,
			FonteNegociacaoServiceImpl fonteNegociacaoService) {
		this.repository = repository;
		this.publisher = publisher;
		this.fonteNegociacaoService = fonteNegociacaoService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
	public Page<FonteNegociacao> pesquisar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
		return repository.filtrar(fonteNegociacaoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
	public ResponseEntity<FonteNegociacao> cadastrar(@Valid @RequestBody FonteNegociacao fonteNegociacao,
			HttpServletResponse response) {
		FonteNegociacao criarCliente = fonteNegociacaoService.save(fonteNegociacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
	ResponseEntity<FonteNegociacao> atualizar(@Valid @RequestBody FonteNegociacao fonteNegociacao,
			@PathVariable Long id, HttpServletResponse response) {
		try {
			FonteNegociacao novaFonteNegociacao = fonteNegociacaoService.atualizar(id, fonteNegociacao);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaFonteNegociacao.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaFonteNegociacao);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "a fonte de negociação");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_FONTE') and #oauth2.hasScope('write')")
	public ResponseEntity<FonteNegociacao> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "a fonte de negociação");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
	public ResponseEntity<FonteNegociacao> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "a fonte de negociação")));
	}
}
