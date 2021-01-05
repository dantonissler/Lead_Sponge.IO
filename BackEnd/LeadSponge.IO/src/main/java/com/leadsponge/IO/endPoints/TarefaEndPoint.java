package com.leadsponge.IO.endPoints;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.repository.Filter.TarefaFilter;
import com.leadsponge.IO.repository.projection.ResumoTarefa;
import com.leadsponge.IO.repository.tarefa.TarefaRepository;
import com.leadsponge.IO.services.implementated.TarefaServiceImpl;

@RestController
@RequestMapping("/tarefas")
class TarefaEndPoint extends ErroMessage {

	@Autowired
	private final TarefaRepository repository;

	@Autowired
	private final TarefaServiceImpl tarefaService;

	@Autowired
	private final ApplicationEventPublisher publisher;

	TarefaEndPoint(TarefaRepository repository, ApplicationEventPublisher publisher, TarefaServiceImpl tarefaService) {
		this.repository = repository;
		this.publisher = publisher;
		this.tarefaService = tarefaService;
	}

	@GetMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
	Page<Tarefa> pesquisar(TarefaFilter tarefaFilter, Pageable pageable) {
		return repository.filtrar(tarefaFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
	Page<ResumoTarefa> resumir(TarefaFilter tarefaFilter, Pageable pageable) {
		return repository.resumir(tarefaFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
	ResponseEntity<Tarefa> cadastrar(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) {
		Tarefa criarTarefa = tarefaService.save(tarefa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, criarTarefa.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('CADASTRAR_TAREFA') and #oauth2.hasScope('write')")
	ResponseEntity<Tarefa> atualizar(@Valid @RequestBody Tarefa tarefa, @PathVariable Long id,
			HttpServletResponse response) {
		try {
			Tarefa novaTarefa = tarefaService.atualizar(id, tarefa);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaTarefa.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "a tarefa");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('REMOVER_TAREFA') and #oauth2.hasScope('write')")
	ResponseEntity<Tarefa> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "a tarefa");
		}
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_TAREFA') and #oauth2.hasScope('read')")
	ResponseEntity<Tarefa> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "a tarefa")));
	}
}
