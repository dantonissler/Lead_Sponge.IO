package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.contato.Contato;
import br.com.blinkdev.leadsponge.models.contato.ContatoFilter;
import br.com.blinkdev.leadsponge.services.ContatoService;
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
@RequestMapping("/contatos")
class ContatoEndPoint {

    @Autowired
    private final ContatoService contatoService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CONTATO') and #oauth2.hasScope('read')")
    Page<Contato> entryPoint(ContatoFilter contatoFilter, Pageable pageable) {
        return contatoService.filtrar(contatoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
    ResponseEntity<Contato> cadastrar(@Valid @RequestBody Contato contato, HttpServletResponse response) {
        Contato criarCliente = contatoService.salvar(contato);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarCliente.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CONTATO') and #oauth2.hasScope('write')")
    ResponseEntity<Contato> atualizar(@Valid @RequestBody Contato contato, @PathVariable Long id, HttpServletResponse response) {
        Contato novaContato = contatoService.atualizar(id, contato);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaContato.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaContato);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CONTATO') and #oauth2.hasScope('write')")
    ResponseEntity<Contato> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    ResponseEntity<Contato> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(contatoService.detalhar(id));
    }
}
