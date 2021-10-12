package br.com.blinkdev.leadsponge.endpoints.task.repository;

import br.com.blinkdev.leadsponge.endpoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endpoints.task.filter.TaskFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryQuery {
    Page<TaskEntity> filtrar(TaskFilter tarefaFilter, Pageable pageable);
}