package br.com.blinkdev.leadsponge.endPoints.task.controller;

import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endPoints.task.filter.TaskFilter;
import br.com.blinkdev.leadsponge.endPoints.task.model.TaskModel;
import br.com.blinkdev.leadsponge.endPoints.task.service.TaskService;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
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
@RequestMapping(value = "tasks", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Tasks")
class TaskController extends ErroMessage {

    @Autowired
    private final TaskService tarefaService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    Page<TaskEntity> list(TaskFilter tarefaFilter, Pageable pageable) {
        return tarefaService.filtrar(tarefaFilter, pageable);
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    Page<TaskModel> resumir(TaskFilter tarefaFilter, Pageable pageable) {
        return tarefaService.resumir(tarefaFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<TaskEntity> cadastrar(@Valid @RequestBody TaskEntity tarefa, HttpServletResponse response) {
        TaskEntity criarTarefa = tarefaService.salvar(tarefa);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarTarefa.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<TaskEntity> atualizar(@Valid @RequestBody TaskEntity tarefa, @PathVariable Long id, HttpServletResponse response) {
        TaskEntity novaTarefa = tarefaService.atualizar(id, tarefa);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaTarefa.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_TAREFA') and #oauth2.hasScope('write')")
    ResponseEntity<TaskEntity> remover(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    ResponseEntity<TaskEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(tarefaService.detalhar(id));
    }
}
