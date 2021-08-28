package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.services.MotivoPerdaService;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.repository.Filter.MotivoPerdaFilter;
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
@RequestMapping("/motivoperda")
class MotivoPerdaEndPoint {

    @Autowired
    private final MotivoPerdaService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    Page<MotivoPerda> pesquisar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable) {
        return service.filtrar(motivoPerdaFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    ResponseEntity<MotivoPerda> cadastrar(@Valid @RequestBody MotivoPerda motivoPerda, HttpServletResponse response) {
        MotivoPerda criarMotivoPerda = service.salvar(motivoPerda);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarMotivoPerda.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarMotivoPerda);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    ResponseEntity<MotivoPerda> atualizar(@Valid @RequestBody MotivoPerda motivoPerda, @PathVariable Long id, HttpServletResponse response) {
        MotivoPerda novoMotivoPerda = service.atualizar(id, motivoPerda);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novoMotivoPerda.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMotivoPerda);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
    ResponseEntity<MotivoPerda> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    ResponseEntity<MotivoPerda> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }
}
