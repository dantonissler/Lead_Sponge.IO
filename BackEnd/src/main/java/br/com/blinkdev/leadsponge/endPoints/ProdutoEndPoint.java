package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.produto.Produto;
import br.com.blinkdev.leadsponge.models.produto.ProdutoFilter;
import br.com.blinkdev.leadsponge.services.produto.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
class ProdutoEndPoint extends ErroMessage {

    @Autowired
    private final ProdutoService produtoService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
    public Page<Produto> entryPoint(ProdutoFilter produtoFilter, Pageable pageable) {
        return produtoService.filtrar(produtoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto, HttpServletResponse response) {
        Produto criarProduto = produtoService.salvar(produto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarProduto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
    ResponseEntity<Produto> atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id, HttpServletResponse response) {
        Produto novaProduto = produtoService.atualizar(id, produto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaProduto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaProduto);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_PRODUTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Produto> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Produto> detalhar(@Valid @PathVariable("id") Long id, HttpServletResponse response) {
        publisher.publishEvent(new RecursoCriadoEvent(this, response, id));
        return ResponseEntity.ok(produtoService.detalhar(id));
    }

    @PutMapping("/{id}/vasivel")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Produto> atualizarPropriedadeVisibilidade(@PathVariable Long id, @RequestBody Boolean visibilidade) {
        produtoService.atualizarPropriedadeVisibilidade(id, visibilidade);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
