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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.services.NegociacaoProdutoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/negociacaoProduto")
class NegociacaoProdutoEndPoint {

	@Autowired
	private final NegociacaoProdutoService service;

	@Autowired
	private final ApplicationEventPublisher publisher;

	@PostMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<NegociacaoProduto> cadastrar(@RequestBody NegociacaoProduto nProduto, HttpServletResponse response) {
		NegociacaoProduto criarNP = service.salvar(nProduto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarNP.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarNP);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<NegociacaoProduto> atualizar(@Valid @RequestBody NegociacaoProduto nProduto, @PathVariable Long id, HttpServletResponse response) {
		NegociacaoProduto novoNegociacaoProduto = service.atualizar(id, nProduto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, novoNegociacaoProduto.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(novoNegociacaoProduto);
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
	ResponseEntity<NegociacaoProduto> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(service.detalhar(id));
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<NegociacaoProduto> remover(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletar(id));
	}
}
