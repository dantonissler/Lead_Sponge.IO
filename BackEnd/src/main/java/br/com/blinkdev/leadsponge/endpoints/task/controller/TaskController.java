package br.com.blinkdev.leadsponge.endpoints.task.controller;

import br.com.blinkdev.leadsponge.endpoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endpoints.task.filter.TaskFilter;
import br.com.blinkdev.leadsponge.endpoints.task.model.TaskModel;
import br.com.blinkdev.leadsponge.endpoints.task.service.TaskService;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
@RequestMapping(value = "tasks", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TaskController extends ErroMessage {
    private final TaskService tarefaService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get custumer by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    public TaskModel getById(@Valid @PathVariable("id") Long id) {
        return tarefaService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search custumers with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
    public PagedModel<TaskModel> searchWithFilters(TaskFilter tarefaFilter, Pageable pageable) {
        return tarefaService.searchWithFilters(tarefaFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    public TaskModel save(@Valid @RequestBody TaskEntity tarefa, HttpServletResponse response) {
        TaskModel criarTarefa = tarefaService.save(tarefa);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarTarefa.getId()));
        return criarTarefa;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
    public TaskModel patch(@Valid @RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        TaskModel novaTarefa = tarefaService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaTarefa.getId()));
        return novaTarefa;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete custumer.")
    @PreAuthorize("hasAuthority('REMOVER_TAREFA') and #oauth2.hasScope('write')")
    public TaskModel delete(@PathVariable Long id) {
        return tarefaService.delete(id);
    }

}
