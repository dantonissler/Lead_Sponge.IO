package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.controller;

import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter.NegotiationProductFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.service.NegotiationProductService;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "negotiationproducts", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Negotiations Products")
class NegotiationProductController {

    @Autowired
    private final NegotiationProductService negotiationProductService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public Page<NegotiationProductEntity> list(NegotiationProductFilter negociacaoProdutoFilter, Pageable pageable) {
        return negotiationProductService.filtrar(negociacaoProdutoFilter, pageable);
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationProductEntity> cadastrar(@Valid @RequestBody NegotiationProductEntity nProduto, HttpServletResponse response) {
        NegotiationProductEntity criarNP = negotiationProductService.salvar(nProduto);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarNP.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarNP);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationProductEntity> atualizar(@Valid @RequestBody NegotiationProductEntity nProduto, @PathVariable Long id, HttpServletResponse response) {
        NegotiationProductEntity novoNegociacaoProduto = negotiationProductService.atualizar(id, nProduto);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novoNegociacaoProduto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNegociacaoProduto);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    ResponseEntity<NegotiationProductEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(negotiationProductService.detalhar(id));
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationProductEntity> remover(@PathVariable Long id) {
        return ResponseEntity.ok(negotiationProductService.deletar(id));
    }
}
