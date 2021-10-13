package br.com.blinkdev.leadsponge.end_points.customer.controller;

import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.customer.filter.CustomerFilter;
import br.com.blinkdev.leadsponge.end_points.customer.model.CustomerModel;
import br.com.blinkdev.leadsponge.end_points.customer.service.CustomerService;
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
@RequestMapping(value = "customers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Customers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CustomerController {
    private final CustomerService customerService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get custumer by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
    public CustomerModel getById(@PathVariable("id") Long id) {
        return customerService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search custumers with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
    public PagedModel<CustomerModel> searchWithFilters(CustomerFilter customerFilter, Pageable pageable) {
        return customerService.searchWithFilters(customerFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
    public CustomerModel save(@Valid @RequestBody CustomerEntity customer, HttpServletResponse response) {
        CustomerModel customerModel = customerService.save(customer);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, customerModel.getId()));
        return customerModel;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
    public CustomerModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        CustomerModel customerModel = customerService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, customerModel.getId()));
        return customerModel;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete custumer.")
    @PreAuthorize("hasAuthority('REMOVER_CONTATO') and #oauth2.hasScope('write')")
    public CustomerModel delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
}
