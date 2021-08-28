package br.com.blinkdev.leadsponge.endPoints;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.blinkdev.leadsponge.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.email.Email;
import br.com.blinkdev.leadsponge.repository.Filter.EmailFilter;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/emails")
class EmailEndPoint {

    @Autowired
    private final EmailService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    Page<Email> pesquisar(EmailFilter emailFilter, Pageable pageable) {
        return service.filtrar(emailFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<Email> cadastrar(@Valid @RequestBody Email email, HttpServletResponse response) {
        Email criarEmail = service.salvar(email);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarEmail.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarEmail);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<Email> atualizar(@Valid @RequestBody Email email, @PathVariable Long id, HttpServletResponse response) {
        Email novaEmail = service.atualizar(id, email);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaEmail.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmail);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    ResponseEntity<Email> detalhar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CLIENTE') and #oauth2.hasScope('write')")
    ResponseEntity<Email> remover(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }
}
