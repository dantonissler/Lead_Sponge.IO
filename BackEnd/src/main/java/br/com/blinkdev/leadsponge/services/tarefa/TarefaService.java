package br.com.blinkdev.leadsponge.services.tarefa;

import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import br.com.blinkdev.leadsponge.models.tarefa.TarefaFilter;
import br.com.blinkdev.leadsponge.models.tarefa.TarefaResumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
