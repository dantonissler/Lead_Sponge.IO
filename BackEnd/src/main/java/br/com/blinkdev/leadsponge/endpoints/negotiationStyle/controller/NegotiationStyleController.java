package br.com.blinkdev.leadsponge.endpoints.negotiationStyle.controller;

import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.filter.NegotiationStyleFilter;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.model.NegotiationStyleModel;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.service.NegotiationStyleService;
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
@RequestMapping(value = "negotiationstyle", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Negotiations Styles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class NegotiationStyleController {
    private final NegotiationStyleService negotiationStyleService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get negotiation style by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
    public NegotiationStyleModel getById(@PathVariable("id") Long id) {
        return negotiationStyleService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search negotiations styles with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
    public PagedModel<NegotiationStyleModel> searchWithFilters(NegotiationStyleFilter negotiationStyleFilter, Pageable pageable) {
        return negotiationStyleService.searchWithFilters(negotiationStyleFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save negotiation style.")
    @PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
    public NegotiationStyleModel save(@Valid @RequestBody NegotiationStyleEntity negotiationStyleEntity, HttpServletResponse response) {
        NegotiationStyleModel negotiationStyleModel = negotiationStyleService.save(negotiationStyleEntity);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, negotiationStyleModel.getId()));
        return negotiationStyleModel;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch negotiation style.")
    @PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
    public NegotiationStyleModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        NegotiationStyleModel negotiationStyleModel = negotiationStyleService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, negotiationStyleModel.getId()));
        return negotiationStyleModel;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete negotiation style.")
    @PreAuthorize("hasAuthority('REMOVER_ESTAGIO') and #oauth2.hasScope('write')")
    public NegotiationStyleModel delete(@PathVariable Long id) {
        return negotiationStyleService.delete(id);
    }
}
