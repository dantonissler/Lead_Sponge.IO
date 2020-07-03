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
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
import com.leadsponge.IO.repository.Filter.MotivoPerdaFilter;
import com.leadsponge.IO.repository.motivoPerda.MotivoPerdaRepository;
import com.leadsponge.IO.services.MotivoPerdaService;

@RestController
@RequestMapping("/motivoperda")
class MotivoPerdaEndPoint extends CrudController {

	@Autowired
	private final MotivoPerdaRepository repository;

	@Autowired
	private final MotivoPerdaService motivoPerdaService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	MotivoPerdaEndPoint(MotivoPerdaRepository repository, ApplicationEventPublisher publisher,
			MotivoPerdaService motivoPerdaService) {
		this.repository = repository;
		this.publisher = publisher;
		this.motivoPerdaService = motivoPerdaService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
	Page<MotivoPerda> pesquisar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable) {
		return repository.filtrar(motivoPerdaFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
	ResponseEntity<MotivoPerda> cadastrar(@Valid @RequestBody MotivoPerda motivoPerda, HttpServletResponse response) {
		MotivoPerda criarMotivoPerda = motivoPerdaService.save(motivoPerda);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarMotivoPerda.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarMotivoPerda);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
	ResponseEntity<MotivoPerda> atualizar(@Valid @RequestBody MotivoPerda motivoPerda, @PathVariable Long id,
			HttpServletResponse response) {
		try {
			MotivoPerda novaMotivoPerda = motivoPerdaService.atualizar(id, motivoPerda);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaMotivoPerda.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaMotivoPerda);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "o motivo da perda");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
	ResponseEntity<MotivoPerda> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "o motivo da perda");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
	ResponseEntity<MotivoPerda> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o motivo da perda")));
	}
}
