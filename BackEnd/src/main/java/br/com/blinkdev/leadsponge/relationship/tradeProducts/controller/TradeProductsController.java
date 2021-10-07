package br.com.blinkdev.leadsponge.relationship.tradeProducts.controller;

import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.filter.TradeProductsFilter;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.model.TradeProductsModel;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.service.TradeProductsService;
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
@RequestMapping(value = "TradeProductss", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Trade Products")
class TradeProductsController {

    @Autowired
    private final TradeProductsService tradeProductsService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get trade product by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public TradeProductsModel getById(@PathVariable("id") Long id) {
        return tradeProductsService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search trades products with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public PagedModel<TradeProductsModel> searchWithFilters(TradeProductsFilter negociacaoProdutoFilter, Pageable pageable) {
        return tradeProductsService.searchWithFilters(negociacaoProdutoFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save trade product.")
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    public TradeProductsModel save(@Valid @RequestBody TradeProductsEntity TradeProducts, HttpServletResponse response) {
        TradeProductsModel TradeProductsEntity = tradeProductsService.save(TradeProducts);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, TradeProductsEntity.getId()));
        return TradeProductsEntity;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch trade product.")
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    public TradeProductsModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        TradeProductsModel TradeProductsEntity = tradeProductsService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, TradeProductsEntity.getId()));
        return TradeProductsEntity;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete trade product.")
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    public TradeProductsModel delete(@PathVariable Long id) {
        return tradeProductsService.delete(id);
    }
}
