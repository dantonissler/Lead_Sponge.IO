package com.leadsponge.IO.endPoints;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.endPoints.crudEndpoints.CrudController;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.repository.NegociacaoProdutoRepository;
import com.leadsponge.IO.services.NegociacaoProdutoService;
import com.leadsponge.IO.services.NegociacaoService;

@RestController
@RequestMapping("/negociacaoProduto")
class NegociacaoProdutoEndPoint extends CrudController {

	@Autowired
	private final NegociacaoProdutoRepository repository;

	@Autowired
	private final NegociacaoProdutoService service;

	@Autowired
	private final ApplicationEventPublisher publisher;

	@Autowired
	private final NegociacaoService nService;

	public NegociacaoProdutoEndPoint(NegociacaoProdutoRepository repository, NegociacaoProdutoService service,
			ApplicationEventPublisher publisher, NegociacaoService nService) {
		this.repository = repository;
		this.service = service;
		this.publisher = publisher;
		this.nService = nService;
	}

	@PostMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
	ResponseEntity<NegociacaoProduto> cadastrar(@RequestBody NegociacaoProduto nProduto,
			HttpServletResponse response) {
		NegociacaoProduto criarNP = service.salvar(nProduto);
		nService.calculo(nProduto.getNegociacao().getId());
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarNP.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarNP);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
	ResponseEntity<NegociacaoProduto> atualizar(@Valid @RequestBody NegociacaoProduto nProduto,
			@PathVariable Long id, HttpServletResponse response) {
		try {
			NegociacaoProduto novo = service.atualizar(id, nProduto);
			nService.calculo(nProduto.getNegociacao().getId());
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novo.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novo);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "o cliente");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
	public ResponseEntity<NegociacaoProduto> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "a negociação do produto");
		}
	}
}
