package br.com.blinkdev.leadsponge.endPoints.task.repository;

import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>, TaskRepositoryQuery {
    List<TaskEntity> findByHoraMarcadaLessThanEqualAndHoraRealizadaIsNull(LocalDateTime data);
}
