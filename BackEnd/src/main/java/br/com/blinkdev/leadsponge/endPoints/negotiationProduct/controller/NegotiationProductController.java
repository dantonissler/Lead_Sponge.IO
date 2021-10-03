package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.controller;

import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.filter.NegotiationProductFilter;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.model.NegotiationProductModel;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.service.NegotiationProductService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "negotiationproducts", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Negotiations Products")
class NegotiationProductController {

    @Autowired
    private final NegotiationProductService negotiationProductService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get negotiation product by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    NegotiationProductModel getById(@PathVariable("id") Long id) {
        return negotiationProductService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search negotiations products with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public PagedModel<NegotiationProductModel> searchWithFilters(NegotiationProductFilter negociacaoProdutoFilter, Pageable pageable) {
        return negotiationProductService.searchWithFilters(negociacaoProdutoFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save negotiation product.")
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    NegotiationProductModel save(@Valid @RequestBody NegotiationProductEntity negotiationProduct, HttpServletResponse response) {
        NegotiationProductModel negotiationProductEntity = negotiationProductService.save(negotiationProduct);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, negotiationProductEntity.getId()));
        return negotiationProductEntity;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch negotiation product.")
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    NegotiationProductModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        NegotiationProductModel negotiationProductEntity = negotiationProductService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, negotiationProductEntity.getId()));
        return negotiationProductEntity;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete negotiation product.")
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    NegotiationProductModel delete(@PathVariable Long id) {
        return negotiationProductService.delete(id);
    }
}
