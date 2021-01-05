package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.tarefa.Tarefa;

@Service
public interface TarefaService {
	public void avisarSobreTarefasVencidos();

	public Tarefa save(Tarefa tarefa);

	public Tarefa atualizar(Long id, Tarefa tarefa);

}
