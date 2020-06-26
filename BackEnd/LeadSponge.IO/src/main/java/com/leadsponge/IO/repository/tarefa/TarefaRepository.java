package com.leadsponge.IO.repository.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.tarefa.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>, TarefaRepositoryQuery{

}
