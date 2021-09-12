package br.com.blinkdev.leadsponge.endPoints.Product.controller;

import br.com.blinkdev.leadsponge.endPoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endPoints.Product.filter.ProductFilter;
import br.com.blinkdev.leadsponge.endPoints.Product.service.ProductService;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
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
@RequestMapping(value = "products", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Products")
class ProductController extends ErroMessage {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
    public Page<ProductEntity> list(ProductFilter produtoFilter, Pageable pageable) {
        return productService.filtrar(produtoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
    public ResponseEntity<ProductEntity> cadastrar(@Valid @RequestBody ProductEntity produto, HttpServletResponse response) {
        ProductEntity criarProduto = productService.salvar(produto);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarProduto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_PRODUTO') and #oauth2.hasScope('write')")
    ResponseEntity<ProductEntity> atualizar(@Valid @RequestBody ProductEntity produto, @PathVariable Long id, HttpServletResponse response) {
        ProductEntity novaProduto = productService.atualizar(id, produto);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaProduto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaProduto);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_PRODUTO') and #oauth2.hasScope('write')")
    public ResponseEntity<ProductEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_PRODUTO') and #oauth2.hasScope('read')")
    public ResponseEntity<ProductEntity> detalhar(@Valid @PathVariable("id") Long id, HttpServletResponse response) {
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, id));
        return ResponseEntity.ok(productService.detalhar(id));
    }

    @PutMapping("/{id}/vasivel")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<ProductEntity> atualizarPropriedadeVisibilidade(@PathVariable Long id, @RequestBody Boolean visibilidade) {
        productService.atualizarPropriedadeVisibilidade(id, visibilidade);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
