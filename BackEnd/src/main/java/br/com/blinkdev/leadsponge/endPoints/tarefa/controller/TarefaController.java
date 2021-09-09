package br.com.blinkdev.leadsponge.endPoints.tarefa.controller;

import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.filter.TarefaFilter;
import br.com.blinkdev.leadsponge.endPoints.tarefa.model.TarefaModel;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.endPoints.tarefa.service.TarefaService;
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
class TarefaController extends ErroMessage {

    @Autowired
    private final TarefaService tarefaService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    Page<TarefaEntity> list(TarefaFilter tarefaFilter, Pageable pageable) {
        return tarefaService.filtrar(tarefaFilter, pageable);
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    Page<TarefaModel> resumir(TarefaFilter tarefaFilter, Pageable pageable) {
        return tarefaService.resumir(tarefaFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<TarefaEntity> cadastrar(@Valid @RequestBody TarefaEntity tarefa, HttpServletResponse response) {
        TarefaEntity criarTarefa = tarefaService.salvar(tarefa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarTarefa.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<TarefaEntity> atualizar(@Valid @RequestBody TarefaEntity tarefa, @PathVariable Long id, HttpServletResponse response) {
        TarefaEntity novaTarefa = tarefaService.atualizar(id, tarefa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaTarefa.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<TarefaEntity> remover(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    ResponseEntity<TarefaEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(tarefaService.detalhar(id));
    }
}
