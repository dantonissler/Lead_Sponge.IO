package br.com.blinkdev.leadsponge.endPoints.tarefa.repository;

import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.filter.TarefaFilter;
import br.com.blinkdev.leadsponge.endPoints.tarefa.model.TarefaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepositoryQuery {
    Page<TarefaEntity> filtrar(TarefaFilter tarefaFilter, Pageable pageable);

    Page<TarefaModel> resumir(TarefaFilter tarefaFilter, Pageable pageable);
}