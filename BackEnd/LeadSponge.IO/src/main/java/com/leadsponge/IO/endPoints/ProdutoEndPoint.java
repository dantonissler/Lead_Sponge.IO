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
import com.leadsponge.IO.models.produto.Produto;
import com.leadsponge.IO.repository.Filter.ProdutoFilter;
import com.leadsponge.IO.services.ProdutoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
class ProdutoEndPoint extends ErroMessage {

	@Autowired
	private final ProdutoService service;

	@Autowired
	private final ApplicationEventPublisher publisher;

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
	public Page<Produto> pesquisar(ProdutoFilter produtoFilter, Pageable pageable) {
		return service.filtrar(produtoFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto, HttpServletResponse response) {
		Produto criarProduto = service.salvar(produto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarProduto.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
	ResponseEntity<Produto> atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id, HttpServletResponse response) {
		Produto novaProduto = service.atualizar(id, produto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, novaProduto.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(novaProduto);
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_PRODUTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Produto> deletar(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletar(id));
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Produto> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(service.deletar(id));
	}

	@PutMapping("/{id}/vasivel")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Produto> atualizarPropriedadeVisibilidade(@PathVariable Long id, @RequestBody Boolean visibilidade) {
		service.atualizarPropriedadeVisibilidade(id, visibilidade);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
