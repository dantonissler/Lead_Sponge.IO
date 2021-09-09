package br.com.blinkdev.leadsponge.endPoints.telefone.controller;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone;
import br.com.blinkdev.leadsponge.endPoints.telefone.filter.TelefoneFilter;
import br.com.blinkdev.leadsponge.endPoints.telefone.service.TelefoneService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/telefones")
class TelefoneController {

    @Autowired
    private final TelefoneService telefoneService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    Page<Telefone> list(TelefoneFilter telefoneFilter, Pageable pageable) {
        return telefoneService.filtrar(telefoneFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<Telefone> cadastrar(@Valid @RequestBody Telefone telefone, HttpServletResponse response) {
        Telefone criarTelefone = telefoneService.salvar(telefone);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarTelefone.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTelefone);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<Telefone> atualizar(@Valid @RequestBody Telefone telefone, @PathVariable Long id, HttpServletResponse response) {
        Telefone novaTelefone = telefoneService.atualizar(id, telefone);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaTelefone.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTelefone);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    public ResponseEntity<Telefone> detalhar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(telefoneService.detalhar(id));
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    public ResponseEntity<Telefone> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneService.deletar(id));
    }
}
