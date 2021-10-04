package br.com.blinkdev.leadsponge.endPoints.Product.controller;

import br.com.blinkdev.leadsponge.endPoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endPoints.Product.filter.ProductFilter;
import br.com.blinkdev.leadsponge.endPoints.Product.model.ProductModel;
import br.com.blinkdev.leadsponge.endPoints.Product.service.ProductService;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
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
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "products", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Products")
class ProductController extends ErroMessage {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get product by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
    public ProductModel getById(@Valid @PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search products with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
    public PagedModel<ProductModel> searchWithFilters(ProductFilter produtoFilter, Pageable pageable) {
        return productService.searchWithFilters(produtoFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save product.")
    @PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
    public ProductModel save(@Valid @RequestBody ProductEntity produto, HttpServletResponse response) {
        ProductModel criarProduto = productService.save(produto);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarProduto.getId()));
        return criarProduto;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch product.")
    @PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
    public ProductModel patch(@Valid @RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        ProductModel novaProduto = productService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaProduto.getId()));
        return novaProduto;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete product.")
    @PreAuthorize("hasAuthority('REMOVER_PRODUTO') and #oauth2.hasScope('write')")
    public ProductModel delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    // TODO: colocar isso como uma funcionalidade do hateoas
    @PutMapping("/{id}/vasivel")
    @ApiOperation(value = "Update visibility property.")
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<?> updateVisibilityProperty(@PathVariable Long id, @RequestBody Boolean visibilidade) {
        productService.atualizarPropriedadeVisibilidade(id, visibilidade);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
