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
import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.ProdutoFilter;
import com.leadsponge.IO.repository.produto.ProdutoRepository;
import com.leadsponge.IO.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
class ProdutoEndPoint extends CrudController {

	@Autowired
	private final ProdutoRepository repository;

	@Autowired
	private final ProdutoService produtoService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	ProdutoEndPoint(ProdutoRepository repository, ApplicationEventPublisher publisher,
			ProdutoService produtoService) {
		this.repository = repository;
		this.publisher = publisher;
		this.produtoService = produtoService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
	public Page<Produto> pesquisar(ProdutoFilter produtoFilter, Pageable pageable) {
		return repository.filtrar(produtoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto,
			HttpServletResponse response) {
		Produto criarProduto = produtoService.save(produto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarProduto.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
	ResponseEntity<Produto> atualizar(@Valid @RequestBody Produto produto,
			@PathVariable Long id, HttpServletResponse response) {
		try {
			Produto novaProduto = produtoService.atualizar(id, produto);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaProduto.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaProduto);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "o produto");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_PRODUTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Produto> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "o produto");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Produto> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o produto")));
	}
}
