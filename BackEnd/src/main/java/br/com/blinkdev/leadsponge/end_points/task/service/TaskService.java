package br.com.blinkdev.leadsponge.end_points.task.service;

import br.com.blinkdev.leadsponge.end_points.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.end_points.task.filter.TaskFilter;
import br.com.blinkdev.leadsponge.end_points.task.model.TaskModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TaskService {

    TaskModel getById(Long id);

    PagedModel<TaskModel> searchWithFilters(TaskFilter tarefaFilter, Pageable pageable);

    TaskModel save(TaskEntity tarefa);

    TaskModel patch(Long id, Map<Object, Object> fields);

    TaskModel delete(Long id);

}
