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
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.repository.Filter.ContatoFilter;
import com.leadsponge.IO.services.ContatoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/contatos")
class ContatoEndPoint {

	@Autowired
	private final ContatoService service;

	@Autowired
	private final ApplicationEventPublisher publisher;

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
	Page<Contato> pesquisar(ContatoFilter contatoFilter, Pageable pageable) {
		return service.filtrar(contatoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
	ResponseEntity<Contato> cadastrar(@Valid @RequestBody Contato contato, HttpServletResponse response) {
		Contato criarCliente = service.salvar(contato);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
	ResponseEntity<Contato> atualizar(@Valid @RequestBody Contato contato, @PathVariable Long id, HttpServletResponse response) {
		Contato novaContato = service.atualizar(id, contato);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, novaContato.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(novaContato);
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_CONTATO') and #oauth2.hasScope('write')")
	ResponseEntity<Contato> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletar(id));
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
	ResponseEntity<Contato> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(service.detalhar(id));
	}
}
