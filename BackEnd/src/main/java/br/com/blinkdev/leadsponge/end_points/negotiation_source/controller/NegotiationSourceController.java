package br.com.blinkdev.leadsponge.end_points.negotiation_source.controller;

import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.filter.NegotiationSourceFilter;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.model.NegotiationSourceModel;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.service.NegotiationSourceService;
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
@RequestMapping(value = "negotiationsources", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Negotiations Sources")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class NegotiationSourceController {
    private final NegotiationSourceService negotiationSourceService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get negotiation source by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public NegotiationSourceModel getById(@PathVariable("id") Long id) {
        return negotiationSourceService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search negotiations sources with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public PagedModel<NegotiationSourceModel> searchWithFilters(NegotiationSourceFilter negotiationSourceFilter, Pageable pageable) {
        return negotiationSourceService.searchWithFilters(negotiationSourceFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save negotiation source.")
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    public NegotiationSourceModel save(@Valid @RequestBody NegotiationSourceEntity negotiationSource, HttpServletResponse response) {
        NegotiationSourceModel negotiationSourceModel = negotiationSourceService.save(negotiationSource);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, negotiationSourceModel.getId()));
        return negotiationSourceModel;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch negotiation source.")
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    public NegotiationSourceModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        NegotiationSourceModel negotiationSourceModel = negotiationSourceService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, negotiationSourceModel.getId()));
        return negotiationSourceModel;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete negotiation source.")
    @PreAuthorize("hasAuthority('REMOVER_FONTE') and #oauth2.hasScope('write')")
    public NegotiationSourceModel delete(@PathVariable Long id) {
        return negotiationSourceService.delete(id);
    }
}
