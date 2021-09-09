package br.com.blinkdev.leadsponge.endPoints.tarefa.repository;

import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long>, TarefaRepositoryQuery {
	List<TarefaEntity> findByHoraMarcadaLessThanEqualAndHoraRealizadaIsNull(LocalDateTime data);
}
