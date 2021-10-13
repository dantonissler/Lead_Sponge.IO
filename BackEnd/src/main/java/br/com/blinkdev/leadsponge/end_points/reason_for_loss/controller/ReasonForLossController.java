package br.com.blinkdev.leadsponge.end_points.reason_for_loss.controller;

import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.filter.ReasonForLossFilter;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.model.ReasonForLossModel;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.service.ReasonForLossService;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "reasonforlosses", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Reason for the losses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ReasonForLossController {
    private final ReasonForLossService reasonForLossService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get reason for the loss by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ReasonForLossModel getById(@PathVariable("id") Long id) {
        return reasonForLossService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search reason for the losses with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public PagedModel<ReasonForLossModel> searchWithFilters(ReasonForLossFilter motivoPerdaFilter, Pageable pageable) {
        return reasonForLossService.searchWithFilters(motivoPerdaFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save reason for the losses.")
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ReasonForLossModel save(@Valid @RequestBody ReasonForLossEntity motivoPerda, HttpServletResponse response) {
        ReasonForLossModel reasonForLossModel = reasonForLossService.save(motivoPerda);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, reasonForLossModel.getId()));
        return reasonForLossModel;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch reason for the losses.")
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ReasonForLossModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        ReasonForLossModel reasonForLossModel = reasonForLossService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, reasonForLossModel.getId()));
        return reasonForLossModel;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete reason for the losses.")
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
    public ReasonForLossModel delete(@PathVariable Long id) {
        return reasonForLossService.delete(id);
    }
}
