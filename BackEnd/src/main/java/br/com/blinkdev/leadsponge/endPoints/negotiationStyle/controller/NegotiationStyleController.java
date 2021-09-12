package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.controller;

import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.filter.NegotiationStyleFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.service.NegotiationStyleService;
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
@RequestMapping(value = "negotiationstyle", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Negotiations Styles")
class NegotiationStyleController {

    @Autowired
    private final NegotiationStyleService negotiationStyleService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
    Page<NegotiationStyleEntity> list(NegotiationStyleFilter estagioNegociacaoFilter, Pageable pageable) {
        return negotiationStyleService.filtrar(estagioNegociacaoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationStyleEntity> cadastrar(@Valid @RequestBody NegotiationStyleEntity estagioNegociacao, HttpServletResponse response) {
        NegotiationStyleEntity criarEstagioNegociacao = negotiationStyleService.salvar(estagioNegociacao);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarEstagioNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarEstagioNegociacao);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationStyleEntity> atualizar(@Valid @RequestBody NegotiationStyleEntity estagioNegociacao, @PathVariable Long id, HttpServletResponse response) {
        NegotiationStyleEntity novaEstagioNegociacao = negotiationStyleService.atualizar(id, estagioNegociacao);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaEstagioNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEstagioNegociacao);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_ESTAGIO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationStyleEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(negotiationStyleService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
    public ResponseEntity<NegotiationStyleEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(negotiationStyleService.detalhar(id));
    }
}
