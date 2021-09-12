package br.com.blinkdev.leadsponge.endPoints.phone.controller;

import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.filter.PhoneFilter;
import br.com.blinkdev.leadsponge.endPoints.phone.service.PhoneService;
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
@RequestMapping(value = "phones", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Phone")
class PhoneController {

    @Autowired
    private final PhoneService phoneService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    Page<PhoneEntity> list(PhoneFilter telefoneFilter, Pageable pageable) {
        return phoneService.filtrar(telefoneFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<PhoneEntity> cadastrar(@Valid @RequestBody PhoneEntity telefone, HttpServletResponse response) {
        PhoneEntity criarTelefone = phoneService.salvar(telefone);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarTelefone.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTelefone);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<PhoneEntity> atualizar(@Valid @RequestBody PhoneEntity telefone, @PathVariable Long id, HttpServletResponse response) {
        PhoneEntity novaTelefone = phoneService.atualizar(id, telefone);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaTelefone.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTelefone);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    public ResponseEntity<PhoneEntity> detalhar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(phoneService.detalhar(id));
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    public ResponseEntity<PhoneEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(phoneService.deletar(id));
    }
}
