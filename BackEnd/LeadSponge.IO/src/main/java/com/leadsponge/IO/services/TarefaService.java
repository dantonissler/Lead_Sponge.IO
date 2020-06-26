package com.leadsponge.IO.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.repository.tarefa.TarefaRepository;
import com.leadsponge.IO.security.exception.UsuarioInativaException;

@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;

	public Tarefa save(Tarefa tarefa) {
		tarefaValidar(tarefa);
		return tarefaRepository.save(tarefa);
	}

	public Tarefa atualizar(Long id, Tarefa tarefa) {
		Tarefa fonteTarefa = buscarProdutoExistente(id);
		BeanUtils.copyProperties(tarefa, fonteTarefa, "id");
		return tarefaRepository.save(fonteTarefa);
	}

	private Tarefa buscarProdutoExistente(Long id) {
		Optional<Tarefa> tarefaSalvo = tarefaRepository.findById(id);
		if (!tarefaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return tarefaSalvo.get();
	}

	private void tarefaValidar(Tarefa tarefa) {
		if (tarefa == null) {
			throw new UsuarioInativaException();
		}
	}
}
