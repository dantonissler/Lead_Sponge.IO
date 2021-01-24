package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.repository.Filter.TarefaFilter;
import com.leadsponge.IO.repository.projection.ResumoTarefa;

@Service
public interface TarefaService {
	public void avisarSobreTarefasVencidos();

	public Tarefa salvar(Tarefa tarefa);

	public Tarefa atualizar(Long id, Tarefa tarefa);

	public Tarefa deletar(Long id);

	public Tarefa detalhar(Long id);

	public Page<Tarefa> filtrar(TarefaFilter tarefaFilter, Pageable pageable);

	public Page<ResumoTarefa> resumir(TarefaFilter tarefaFilter, Pageable pageable);

}
