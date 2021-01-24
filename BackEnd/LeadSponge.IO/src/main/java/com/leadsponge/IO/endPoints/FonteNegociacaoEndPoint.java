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
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.repository.Filter.FonteNegociacaoFilter;
import com.leadsponge.IO.services.implementated.FonteNegociacaoServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/fontes")
class FonteNegociacaoEndPoint {

	@Autowired
	private final FonteNegociacaoServiceImpl service;

	@Autowired
	private final ApplicationEventPublisher publisher;

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
	public Page<FonteNegociacao> pesquisar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
		return service.filtrar(fonteNegociacaoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
	public ResponseEntity<FonteNegociacao> cadastrar(@Valid @RequestBody FonteNegociacao fonteNegociacao, HttpServletResponse response) {
		FonteNegociacao fonteNegociacaoNegociacao = service.salvar(fonteNegociacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, fonteNegociacaoNegociacao.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
	ResponseEntity<FonteNegociacao> atualizar(@Valid @RequestBody FonteNegociacao fonteNegociacao, @PathVariable Long id, HttpServletResponse response) {
		FonteNegociacao fonteNegociacaoNegociacao = service.atualizar(id, fonteNegociacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, fonteNegociacaoNegociacao.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_FONTE') and #oauth2.hasScope('write')")
	public ResponseEntity<FonteNegociacao> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletar(id));
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
	public ResponseEntity<FonteNegociacao> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(service.detalhar(id));
	}
}
