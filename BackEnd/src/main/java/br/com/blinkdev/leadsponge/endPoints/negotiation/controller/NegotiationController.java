package br.com.blinkdev.leadsponge.endPoints.negotiation.controller;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endPoints.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.endPoints.negotiation.service.NegotiationService;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping(value = "negotiations", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Negotiations")
class NegotiationController {

    @Autowired
    private final NegotiationService negotiationService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get negotiation by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
    NegotiationModel getById(@Valid @PathVariable("id") Long id) {
        return negotiationService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {""})
    @ApiOperation(value = "Search negotiations with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
    PagedModel<NegotiationModel> searchWithFilters(NegotiationFilter negociacaoFilter, Pageable pageable) {
        return negotiationService.searchWithFilters(negociacaoFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {""})
    @ApiOperation(value = "Save negotiation.")
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    NegotiationModel save(@Valid @RequestBody NegotiationEntity negociacao, HttpServletResponse response) {
        NegotiationModel criarNegociacao = negotiationService.save(negociacao);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarNegociacao.getId()));
        return criarNegociacao;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch negotiation.")
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    NegotiationModel patch(@RequestBody NegotiationEntity negociacao, @PathVariable Long id, HttpServletResponse response) {
        NegotiationModel novonegociacao = negotiationService.patch(id, negociacao);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novonegociacao.getId()));
        return novonegociacao;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_NEGOCIACAO') and #oauth2.hasScope('write')")
    NegotiationModel delete(@PathVariable Long id) {
        return negotiationService.deletar(id);
    }

    @PutMapping("/{id}/avaliacao")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationEntity> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Integer avaliacao) {
        negotiationService.atualizarPropriedadeAvaliacao(id, avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/estagio")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationEntity> atualizarPropriedadeEstagio(@PathVariable Long id, @RequestBody NegotiationStyleEntity estagio) {
        negotiationService.atualizarPropriedadeEstagio(id, estagio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/estatus")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationEntity> atualizarPropriedadeEstatus(@PathVariable Long id, @RequestBody StatusNegotiation estatus) {
        negotiationService.atualizarPropriedadeEstatus(id, estatus);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/dataFim")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationEntity> atualizarPropriedadeDataFim(@PathVariable Long id, @RequestBody LocalDateTime data) {
        negotiationService.atualizarPropriedadeDataFim(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/perda")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<NegotiationEntity> atribuirMotivoPerda(@PathVariable Long id, @RequestBody ReasonForLossEntity morivoPerda) {
        negotiationService.atribuirPropMP(id, morivoPerda);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
