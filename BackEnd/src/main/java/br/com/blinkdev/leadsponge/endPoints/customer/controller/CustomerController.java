package br.com.blinkdev.leadsponge.endPoints.customer.controller;

import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.filter.CustomerFilter;
import br.com.blinkdev.leadsponge.endPoints.customer.service.CustomerService;
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
@RequestMapping(value = "customers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Customers")
class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    Page<CustomerEntity> list(CustomerFilter clienteFilter, Pageable pageable) {
        return customerService.filtrar(clienteFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<CustomerEntity> cadastrar(@Valid @RequestBody CustomerEntity cliente, HttpServletResponse response) {
        CustomerEntity criarCliente = customerService.salvar(cliente);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarCliente.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<CustomerEntity> atualizar(@Valid @RequestBody CustomerEntity cliente, @PathVariable Long id, HttpServletResponse response) {
        CustomerEntity novoCliente = customerService.atualizar(id, cliente);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novoCliente.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<CustomerEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    ResponseEntity<CustomerEntity> detalhar(@Valid @PathVariable("id") Long id, HttpServletResponse response) {
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, id));
        return ResponseEntity.ok(customerService.detalhar(id));
    }
}
