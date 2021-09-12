package br.com.blinkdev.leadsponge.endPoints.task.repository;

import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endPoints.task.filter.TaskFilter;
import br.com.blinkdev.leadsponge.endPoints.task.model.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryQuery {
    Page<TaskEntity> filtrar(TaskFilter tarefaFilter, Pageable pageable);

    Page<TaskModel> resumir(TaskFilter tarefaFilter, Pageable pageable);
}