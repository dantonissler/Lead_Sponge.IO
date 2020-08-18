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
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.repository.campanha.CampanhaRepository;
import com.leadsponge.IO.services.CampanhaService;

@RestController
@RequestMapping("/campanhas")
class CampanhaEndPoint extends ErroMessage {

	@Autowired
	private final CampanhaRepository repository;

	@Autowired
	private final CampanhaService campanhaService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	CampanhaEndPoint(CampanhaRepository repository, ApplicationEventPublisher publisher,
			CampanhaService campanhaService) {
		this.repository = repository;
		this.publisher = publisher;
		this.campanhaService = campanhaService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
	Page<Campanha> pesquisar(CampanhaFilter campanhaFilter, Pageable pageable) {
		return repository.filtrar(campanhaFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
	ResponseEntity<Campanha> cadastrar(@Valid @RequestBody Campanha campanha, HttpServletResponse response) {
		Campanha criarCliente = campanhaService.save(campanha);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
	ResponseEntity<Campanha> atualizar(@Valid @RequestBody Campanha campanha, @PathVariable Long id,
			HttpServletResponse response) {
		try {
			Campanha novaCampanha = campanhaService.atualizar(id, campanha);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCampanha.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaCampanha);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "a campanha");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
	ResponseEntity<Campanha> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "a campanha");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
	ResponseEntity<Campanha> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha")));
	}
}
