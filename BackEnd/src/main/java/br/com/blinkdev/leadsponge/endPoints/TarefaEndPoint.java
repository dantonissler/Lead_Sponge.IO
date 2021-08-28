package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import br.com.blinkdev.leadsponge.services.TarefaService;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.repository.Filter.TarefaFilter;
import br.com.blinkdev.leadsponge.repository.projection.TarefaResumo;
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
@RequestMapping("/tarefas")
class TarefaEndPoint extends ErroMessage {

    @Autowired
    private final TarefaService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    Page<Tarefa> pesquisar(TarefaFilter tarefaFilter, Pageable pageable) {
        return service.filtrar(tarefaFilter, pageable);
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    Page<TarefaResumo> resumir(TarefaFilter tarefaFilter, Pageable pageable) {
        return service.resumir(tarefaFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<Tarefa> cadastrar(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) {
        Tarefa criarTarefa = service.salvar(tarefa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarTarefa.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<Tarefa> atualizar(@Valid @RequestBody Tarefa tarefa, @PathVariable Long id, HttpServletResponse response) {
        Tarefa novaTarefa = service.atualizar(id, tarefa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaTarefa.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<Tarefa> remover(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    ResponseEntity<Tarefa> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }
}
