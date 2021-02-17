package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.repository.Filter.TarefaFilter;
import com.leadsponge.IO.repository.projection.TarefaResumo;

@Service
public interface TarefaService {
	void avisarSobreTarefasVencidos();

	Tarefa salvar(Tarefa tarefa);

	Tarefa atualizar(Long id, Tarefa tarefa);

	Tarefa deletar(Long id);

	Tarefa detalhar(Long id);

	Page<Tarefa> filtrar(TarefaFilter tarefaFilter, Pageable pageable);

	Page<TarefaResumo> resumir(TarefaFilter tarefaFilter, Pageable pageable);

}
