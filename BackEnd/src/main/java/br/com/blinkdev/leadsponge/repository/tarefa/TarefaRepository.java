package br.com.blinkdev.leadsponge.repository.tarefa;

import java.time.LocalDateTime;
import java.util.List;

import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>, TarefaRepositoryQuery {
	List<Tarefa> findByHoraMarcadaLessThanEqualAndHoraRealizadaIsNull(LocalDateTime data);
}
