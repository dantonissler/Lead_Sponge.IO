package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
import br.com.blinkdev.leadsponge.services.negociacaoProduto.NegociacaoProdutoService;
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
@RequestMapping("/negociacaoProdutos")
class NegociacaoProdutoEndPoint {

    @Autowired
    private final NegociacaoProdutoService negociacaoProdutoService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public Page<NegociacaoProduto> list(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable) {
        return negociacaoProdutoService.filtrar(negociacaoProdutoFilter, pageable);
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegociacaoProduto> cadastrar(@Valid @RequestBody NegociacaoProduto nProduto, HttpServletResponse response) {
        NegociacaoProduto criarNP = negociacaoProdutoService.salvar(nProduto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarNP.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarNP);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<NegociacaoProduto> atualizar(@Valid @RequestBody NegociacaoProduto nProduto, @PathVariable Long id, HttpServletResponse response) {
        NegociacaoProduto novoNegociacaoProduto = negociacaoProdutoService.atualizar(id, nProduto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novoNegociacaoProduto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNegociacaoProduto);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    ResponseEntity<NegociacaoProduto> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(negociacaoProdutoService.detalhar(id));
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<NegociacaoProduto> remover(@PathVariable Long id) {
        return ResponseEntity.ok(negociacaoProdutoService.deletar(id));
    }
}
