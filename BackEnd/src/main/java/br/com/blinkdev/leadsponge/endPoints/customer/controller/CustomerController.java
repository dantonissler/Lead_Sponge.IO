package br.com.blinkdev.leadsponge.endPoints.customer.controller;

import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.filter.CustomerFilter;
import br.com.blinkdev.leadsponge.endPoints.customer.model.CustomerModel;
import br.com.blinkdev.leadsponge.endPoints.customer.service.CustomerService;
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
@RequestMapping(value = "customers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Customers")
class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get custumer by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_Customer') and #oauth2.hasScope('read')")
    CustomerModel getById(@Valid @PathVariable("id") Long id, HttpServletResponse response) {
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, id));
        return customerService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {""})
    @ApiOperation(value = "Search custumers with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
    PagedModel<CustomerModel> searchWithFilters(CustomerFilter customerFilter, Pageable pageable) {
        return customerService.searchWithFilters(customerFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {""})
    @ApiOperation(value = "Save custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_Customer') and #oauth2.hasScope('write')")
    CustomerModel save(@Valid @RequestBody CustomerEntity customer, HttpServletResponse response) {
        CustomerModel criarCustomer = customerService.save(customer);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarCustomer.getId()));
        return criarCustomer;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_Customer') and #oauth2.hasScope('write')")
    CustomerModel patch(@Valid @RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        CustomerModel novoCustomer = customerService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novoCustomer.getId()));
        return novoCustomer;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete custumer.")
    @PreAuthorize("hasAuthority('REMOVER_Customer') and #oauth2.hasScope('write')")
    CustomerModel delete(@PathVariable Long id) {
        return customerService.delete(id);
    }


}
