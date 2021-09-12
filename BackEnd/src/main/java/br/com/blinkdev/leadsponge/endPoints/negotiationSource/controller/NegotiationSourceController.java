package br.com.blinkdev.leadsponge.endPoints.negotiationSource.controller;

import br.com.blinkdev.leadsponge.endPoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.filter.NegotiationSourceFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.service.NegotiationSourceService;
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
@RequestMapping(value = "negotiationsources", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Negotiations Sources")
class NegotiationSourceController {

    @Autowired
    private final NegotiationSourceService negotiationSourceService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public Page<NegotiationSourceEntity> list(NegotiationSourceFilter fonteNegociacaoFilter, Pageable pageable) {
        return negotiationSourceService.filtrar(fonteNegociacaoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    public ResponseEntity<NegotiationSourceEntity> cadastrar(@Valid @RequestBody NegotiationSourceEntity fonteNegociacao, HttpServletResponse response) {
        NegotiationSourceEntity fonteNegociacaoNegociacao = negotiationSourceService.salvar(fonteNegociacao);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, fonteNegociacaoNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationSourceEntity> atualizar(@Valid @RequestBody NegotiationSourceEntity fonteNegociacao, @PathVariable Long id, HttpServletResponse response) {
        NegotiationSourceEntity fonteNegociacaoNegociacao = negotiationSourceService.atualizar(id, fonteNegociacao);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, fonteNegociacaoNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_FONTE') and #oauth2.hasScope('write')")
    public ResponseEntity<NegotiationSourceEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(negotiationSourceService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public ResponseEntity<NegotiationSourceEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(negotiationSourceService.detalhar(id));
    }
}
