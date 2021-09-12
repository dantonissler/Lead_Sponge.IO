package br.com.blinkdev.leadsponge.endPoints.task.service;

import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endPoints.task.filter.TaskFilter;
import br.com.blinkdev.leadsponge.endPoints.task.model.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    void avisarSobreTarefasVencidos();

    TaskEntity salvar(TaskEntity tarefa);

    TaskEntity atualizar(Long id, TaskEntity tarefa);

    TaskEntity deletar(Long id);

    TaskEntity detalhar(Long id);

    Page<TaskEntity> filtrar(TaskFilter tarefaFilter, Pageable pageable);

    Page<TaskModel> resumir(TaskFilter tarefaFilter, Pageable pageable);

}
