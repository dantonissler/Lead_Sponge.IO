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
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.Filter.SegmentoFilter;
import com.leadsponge.IO.repository.segmento.SegmentoRepository;
import com.leadsponge.IO.services.SegmentoService;

@RestController
@RequestMapping("segmentos")
class SegmentoEndPoint extends CrudController {

	@Autowired
	private final SegmentoRepository repository;

	@Autowired
	private final SegmentoService segmentoService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	SegmentoEndPoint(SegmentoRepository repository, ApplicationEventPublisher publisher,
			SegmentoService segmentoService) {
		this.repository = repository;
		this.publisher = publisher;
		this.segmentoService = segmentoService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
	public Page<Segmento> pesquisar(SegmentoFilter segmentoFilter, Pageable pageable) {
		return repository.filtrar(segmentoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Segmento> cadastrar(@Valid @RequestBody Segmento segmento,
			HttpServletResponse response) {
		Segmento criarSegmento = segmentoService.save(segmento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarSegmento.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarSegmento);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
	ResponseEntity<Segmento> atualizar(@Valid @RequestBody Segmento segmento,
			@PathVariable Long id, HttpServletResponse response) {
		try {
			Segmento novaSegmento = segmentoService.atualizar(id, segmento);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaSegmento.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaSegmento);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "a estagio de negociação");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_SEGMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Segmento> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "a estagio de negociação");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Segmento> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "a estagio de negociação")));
	}

}
