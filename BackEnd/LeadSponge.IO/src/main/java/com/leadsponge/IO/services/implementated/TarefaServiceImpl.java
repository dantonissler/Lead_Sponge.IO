package com.leadsponge.IO.services.implementated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.exception.UsuarioInativaException;
import com.leadsponge.IO.mail.Mailer;
import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.tarefa.TarefaRepository;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.services.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	private static final String DESTINATARIOS = "PESQUISAR_TAREFA";

	private static final Logger logger = LoggerFactory.getLogger(TarefaServiceImpl.class);

	@Autowired
	private TarefaRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Mailer mailer;

	@Override
	@Scheduled(cron = "0 0 6 * * *")
	public void avisarSobreTarefasVencidos() {
		if (logger.isDebugEnabled()) {
			logger.debug("Preparando envio de " + "e-mails de aviso de tarefas vencidos.");
		}
		List<Tarefa> vencidos = repository.findByHoraMarcadaLessThanEqualAndHoraRealizadaIsNull(LocalDateTime.now());
		if (vencidos.isEmpty()) {
			logger.info("Sem tarefas vencidos para aviso.");
			return;
		}
		logger.info("Exitem {} tarefas vencidos.", vencidos.size());
		List<Usuario> destinatarios = usuarioRepository.findByRolesNome(DESTINATARIOS);
		if (destinatarios.isEmpty()) {
			logger.warn("Existem tarefas vencidos, mas o " + "sistema não encontrou destinatários.");
			return;
		}
		mailer.avisarSobreTarefasVencidas(vencidos, destinatarios);
		logger.info("Envio de e-mail de aviso concluído.");
	}

	@Override
	public Tarefa save(Tarefa tarefa) {
		tarefaValidar(tarefa);
		return repository.save(tarefa);
	}

	@Override
	public Tarefa atualizar(Long id, Tarefa tarefa) {
		Tarefa fonteTarefa = buscarProdutoExistente(id);
		BeanUtils.copyProperties(tarefa, fonteTarefa, "id");
		return repository.save(fonteTarefa);
	}

	private Tarefa buscarProdutoExistente(Long id) {
		Optional<Tarefa> tarefaSalvo = repository.findById(id);
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
