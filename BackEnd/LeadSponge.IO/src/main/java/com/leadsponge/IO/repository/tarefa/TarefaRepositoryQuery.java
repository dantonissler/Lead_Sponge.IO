package com.leadsponge.IO.repository.tarefa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.repository.Filter.TarefaFilter;
import com.leadsponge.IO.repository.projection.TarefaResumo;

@Repository
public interface TarefaRepositoryQuery {
    Page<Tarefa> filtrar(TarefaFilter tarefaFilter, Pageable pageable);

    Page<TarefaResumo> resumir(TarefaFilter tarefaFilter, Pageable pageable);
}