package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.controller;

import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.filter.ReasonForLossFilter;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.service.ReasonForLossService;
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
@RequestMapping(value = "reasonforlosses", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Reason for the losses")
class ReasonForLossController {

    @Autowired
    private final ReasonForLossService reasonForLossService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    Page<ReasonForLossEntity> list(ReasonForLossFilter motivoPerdaFilter, Pageable pageable) {
        return reasonForLossService.filtrar(motivoPerdaFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    ResponseEntity<ReasonForLossEntity> cadastrar(@Valid @RequestBody ReasonForLossEntity motivoPerda, HttpServletResponse response) {
        ReasonForLossEntity criarMotivoPerda = reasonForLossService.salvar(motivoPerda);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarMotivoPerda.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarMotivoPerda);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    ResponseEntity<ReasonForLossEntity> atualizar(@Valid @RequestBody ReasonForLossEntity motivoPerda, @PathVariable Long id, HttpServletResponse response) {
        ReasonForLossEntity novoMotivoPerda = reasonForLossService.atualizar(id, motivoPerda);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novoMotivoPerda.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMotivoPerda);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
    ResponseEntity<ReasonForLossEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(reasonForLossService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    ResponseEntity<ReasonForLossEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(reasonForLossService.detalhar(id));
    }
}
