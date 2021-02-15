package com.leadsponge.IO.endPoints;

import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.repository.Filter.NegociacaoProdutoFilter;
import com.leadsponge.IO.services.NegociacaoProdutoService;
import io.swagger.annotations.ApiOperation;
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
    private final NegociacaoProdutoService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ApiOperation(value = "Pesquisar campanhas")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public Page<NegociacaoProduto> pesquisar(NegociacaoProdutoFilter negociacaoProdutoFilter, Pageable pageable) {
        return service.filtrar(negociacaoProdutoFilter, pageable);
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegociacaoProduto> cadastrar(@Valid @RequestBody NegociacaoProduto nProduto, HttpServletResponse response) {
        NegociacaoProduto criarNP = service.salvar(nProduto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarNP.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarNP);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<NegociacaoProduto> atualizar(@Valid @RequestBody NegociacaoProduto nProduto, @PathVariable Long id, HttpServletResponse response) {
        NegociacaoProduto novoNegociacaoProduto = service.atualizar(id, nProduto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novoNegociacaoProduto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNegociacaoProduto);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    ResponseEntity<NegociacaoProduto> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<NegociacaoProduto> remover(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }
}
