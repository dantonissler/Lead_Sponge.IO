package br.com.blinkdev.leadsponge.endPoints.tarefa.service;

import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.filter.TarefaFilter;
import br.com.blinkdev.leadsponge.endPoints.tarefa.model.TarefaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TarefaService {
	void avisarSobreTarefasVencidos();

	TarefaEntity salvar(TarefaEntity tarefa);

	TarefaEntity atualizar(Long id, TarefaEntity tarefa);

	TarefaEntity deletar(Long id);

	TarefaEntity detalhar(Long id);

	Page<TarefaEntity> filtrar(TarefaFilter tarefaFilter, Pageable pageable);

	Page<TarefaModel> resumir(TarefaFilter tarefaFilter, Pageable pageable);

}
