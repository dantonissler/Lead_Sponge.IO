package br.com.blinkdev.leadsponge.endPoints.task.service;

import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endPoints.task.filter.TaskFilter;
import br.com.blinkdev.leadsponge.endPoints.task.model.TaskModel;
import br.com.blinkdev.leadsponge.endPoints.task.repository.TaskRepository;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.repository.UserRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.errorValidate.exception.UsuarioInativaException;
import br.com.blinkdev.leadsponge.utils.mail.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl extends ErroMessage implements TaskService {

	private static final String DESTINATARIOS = "PESQUISAR_TAREFA";

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskRepository repository;

	@Autowired
	private UserRepository usuarioRepository;

	@Autowired
	private Mailer mailer;

	@Override
	@Scheduled(cron = "0 0 6 * * *")
	public void avisarSobreTarefasVencidos() {
		if (logger.isDebugEnabled()) {
			logger.debug("Preparando envio de " + "e-mails de aviso de tarefas vencidos.");
		}
		List<TaskEntity> vencidos = repository.findByHoraMarcadaLessThanEqualAndHoraRealizadaIsNull(LocalDateTime.now());
		if (vencidos.isEmpty()) {
			logger.info("Sem tarefas vencidos para aviso.");
			return;
		}
		logger.info("Exitem {} tarefas vencidos.", vencidos.size());
		List<UserEntity> destinatarios = usuarioRepository.findByRolesNome(DESTINATARIOS);
		if (destinatarios.isEmpty()) {
			logger.warn("Existem tarefas vencidos, mas o " + "sistema não encontrou destinatários.");
			return;
		}
		mailer.avisarSobreTarefasVencidas(vencidos, destinatarios);
		logger.info("Envio de e-mail de aviso concluído.");
	}

	@Override
	public TaskEntity salvar(TaskEntity tarefa) {
		tarefaValidar(tarefa);
		return repository.save(tarefa);
	}

	@Override
	public TaskEntity atualizar(Long id, TaskEntity tarefa) {
		TaskEntity fonteTarefa = buscarProdutoExistente(id);
		BeanUtils.copyProperties(tarefa, fonteTarefa, "id");
		return repository.save(fonteTarefa);
	}

	private TaskEntity buscarProdutoExistente(Long id) {
		Optional<TaskEntity> tarefaSalvo = repository.findById(id);
		if (!tarefaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return tarefaSalvo.get();
	}

	private void tarefaValidar(TaskEntity tarefa) {
		if (tarefa == null) {
			throw new UsuarioInativaException();
		}
	}

	@Override
	public Page<TaskEntity> filtrar(TaskFilter tarefaFilter, Pageable pageable) {
		return repository.filtrar(tarefaFilter, pageable);
	}

	@Override
	public TaskEntity deletar(Long id) {
		TaskEntity tarefaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		repository.deleteById(id);
		return tarefaSalvo;
	}

	@Override
	public TaskEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
	}

	@Override
	public Page<TaskModel> resumir(TaskFilter tarefaFilter, Pageable pageable) {
		return repository.resumir(tarefaFilter, pageable);
	}
}
