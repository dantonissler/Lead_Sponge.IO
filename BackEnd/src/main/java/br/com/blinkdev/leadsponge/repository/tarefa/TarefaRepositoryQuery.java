package br.com.blinkdev.leadsponge.repository.tarefa;

import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.blinkdev.leadsponge.repository.Filter.TarefaFilter;
import br.com.blinkdev.leadsponge.repository.projection.TarefaResumo;

@Repository
public interface TarefaRepositoryQuery {
    Page<Tarefa> filtrar(TarefaFilter tarefaFilter, Pageable pageable);

    Page<TarefaResumo> resumir(TarefaFilter tarefaFilter, Pageable pageable);
}