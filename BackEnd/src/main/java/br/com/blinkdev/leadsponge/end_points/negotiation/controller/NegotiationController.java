package br.com.blinkdev.leadsponge.end_points.negotiation.controller;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.end_points.negotiation.filter.NegotiationFilter;
import br.com.blinkdev.leadsponge.end_points.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.end_points.negotiation.service.NegotiationService;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import java.util.Map;

@RestController
@RequestMapping(value = "negotiations", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Tag(name = "Negotiations", description = "To start a negotiation it is necessary to have the following objects previously created: Customer, Campaign and Negotiation Source.")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class NegotiationController {
    private final NegotiationService negotiationService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get negotiation by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
    public NegotiationModel getById(@Valid @PathVariable("id") Long id) {
        return negotiationService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search negotiations with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
    public PagedModel<NegotiationModel> searchWithFilters(NegotiationFilter negotiationFilter, Pageable pageable) {
        return negotiationService.searchWithFilters(negotiationFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save negotiation.")
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public NegotiationModel save(@Valid @RequestBody NegotiationEntity negotiationEntity, HttpServletResponse response) {
        NegotiationModel criarNegociacao = negotiationService.save(negotiationEntity);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarNegociacao.getId()));
        return criarNegociacao;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch negotiation.")
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public NegotiationModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        NegotiationModel novonegociacao = negotiationService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novonegociacao.getId()));
        return novonegociacao;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_NEGOCIACAO') and #oauth2.hasScope('write')")
    public NegotiationModel delete(@PathVariable Long id) {
        return negotiationService.deletar(id);
    }

    @PutMapping("/{id}/avaliacao")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NegotiationEntity> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Integer avaliacao) {
        negotiationService.atualizarPropriedadeAvaliacao(id, avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/estagio")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NegotiationEntity> atualizarPropriedadeEstagio(@PathVariable Long id, @RequestBody NegotiationStyleEntity estagio) {
        negotiationService.updateNegotiationStyle(id, estagio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/estatus")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NegotiationEntity> atualizarPropriedadeEstatus(@PathVariable Long id, @RequestBody StatusNegotiation estatus) {
        negotiationService.updateNegotiationStatus(id, estatus);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/dataFim")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NegotiationEntity> atualizarPropriedadeDataFim(@PathVariable Long id, @RequestBody LocalDateTime data) {
        negotiationService.atualizarPropriedadeDataFim(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/perda")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NegotiationEntity> atribuirMotivoPerda(@PathVariable Long id, @RequestBody ReasonForLossEntity morivoPerda) {
        negotiationService.updateNegotiationRFLE(id, morivoPerda);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
