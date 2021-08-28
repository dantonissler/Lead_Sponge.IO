package br.com.blinkdev.leadsponge.services.implementated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import br.com.blinkdev.leadsponge.services.TarefaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.errorValidate.exception.UsuarioInativaException;
import br.com.blinkdev.leadsponge.mail.Mailer;
import br.com.blinkdev.leadsponge.repository.Filter.TarefaFilter;
import br.com.blinkdev.leadsponge.repository.projection.TarefaResumo;
import br.com.blinkdev.leadsponge.repository.tarefa.TarefaRepository;
import br.com.blinkdev.leadsponge.repository.usuario.UsuarioRepository;

@Service
public class TarefaServiceImpl extends ErroMessage implements TarefaService {

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
	public Tarefa salvar(Tarefa tarefa) {
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

	@Override
	public Page<Tarefa> filtrar(TarefaFilter tarefaFilter, Pageable pageable) {
		return repository.filtrar(tarefaFilter, pageable);
	}

	@Override
	public Tarefa deletar(Long id) {
		Tarefa tarefaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		repository.deleteById(id);
		return tarefaSalvo;
	}

	@Override
	public Tarefa detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
	}

	@Override
	public Page<TarefaResumo> resumir(TarefaFilter tarefaFilter, Pageable pageable) {
		return repository.resumir(tarefaFilter, pageable);
	}
}
