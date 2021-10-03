package br.com.blinkdev.leadsponge.endPoints.contact.controller;

import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endPoints.contact.filter.ContactFilter;
import br.com.blinkdev.leadsponge.endPoints.contact.model.ContactModel;
import br.com.blinkdev.leadsponge.endPoints.contact.service.ContactService;
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
// TODO: Trabalhar com o email, phone e endereço
@RestController
@AllArgsConstructor
@RequestMapping(value = "contacts", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Contacts")
class ContactController {

    @Autowired
    private final ContactService contactService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get contact by ID.")
    @PreAuthorize("hasAuthority('REMOVER_CONTATO') and #oauth2.hasScope('write')")
    ContactModel getById(@PathVariable Long id) {
        return contactService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search contacts with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
    PagedModel<ContactModel> searchWithFilters(ContactFilter contactFilter, Pageable pageable) {
        return contactService.searchWithFilters(contactFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save contact.")
    @PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
    ContactModel save(@RequestBody ContactEntity contato, HttpServletResponse response) {
        ContactModel contactModel = contactService.save(contato);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, contactModel.getId()));
        return contactModel;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"{id}"})
    @ApiOperation(value = "Patch campaign.")
    @PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
    ContactModel patch(@RequestBody Map<Object, Object> contact, @PathVariable Long id, HttpServletResponse response) {
        ContactModel contactModel = contactService.patch(id, contact);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, contactModel.getId()));
        return contactModel;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete campaign.")
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<ContactModel> delete(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(contactService.delete(id));
    }
}
