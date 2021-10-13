package br.com.blinkdev.leadsponge.end_points.task.service;

import br.com.blinkdev.leadsponge.end_points.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.end_points.task.filter.TaskFilter;
import br.com.blinkdev.leadsponge.end_points.task.model.TaskModel;
import br.com.blinkdev.leadsponge.end_points.task.model_assembler.TaskModelAssembler;
import br.com.blinkdev.leadsponge.end_points.task.repository.TaskRepository;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.end_points.user.repository.UserRepository;
import br.com.blinkdev.leadsponge.error_validate.ErroMessage;
import br.com.blinkdev.leadsponge.utils.mail.Mailer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl extends ErroMessage implements TaskService {

    private static final String DESTINATARIOS = "PESQUISAR_TAREFA";
    private final Mailer mailer;
    private final TaskRepository repository;
    private final UserRepository usuarioRepository;
    private final TaskModelAssembler taskModelAssembler;
    private final PagedResourcesAssembler<TaskEntity> assembler;

    @Override
    public TaskModel getById(Long id) {
        log.info("CustomerService - patch");
        return repository.findById(id).map(taskModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[task]"));
    }

    @Override
    public PagedModel<TaskModel> searchWithFilters(TaskFilter tarefaFilter, Pageable pageable) {
        log.info("CustomerService - patch");
        return assembler.toModel(repository.filtrar(tarefaFilter, pageable), taskModelAssembler);
    }

    @Override
    @Transactional
    public TaskModel save(TaskEntity tarefa) {
        log.info("CustomerService - save");
        return taskModelAssembler.toModel(repository.save(tarefa));
    }

    @Override
    @Transactional
    public TaskModel patch(Long id, Map<Object, Object> fields) {
        log.info("CustomerService - patch");
        TaskEntity taskEntity = repository.findById(id).orElseThrow(() -> notFouldId(id, "[task]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(TaskEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, taskEntity, value);
        });
        return save(taskEntity);
    }

    @Override
    @Transactional
    public TaskModel delete(Long id) {
        log.info("CustomerService - patch");
        TaskEntity tarefaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "[task]"));
        repository.deleteById(id);
        return taskModelAssembler.toModel(tarefaSalvo);
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void avisarSobreTarefasVencidos() {
        if (log.isDebugEnabled()) {
            log.debug("Preparando envio de " + "e-mails de aviso de tarefas vencidos.");
        }
        List<TaskEntity> vencidos = repository.findByAppointmentLessThanEqualAndTimePerformedIsNull(LocalDateTime.now());
        if (vencidos.isEmpty()) {
            log.info("Sem tarefas vencidos para aviso.");
            return;
        }
        log.info("Exitem {} tarefas vencidos.", vencidos.size());
        List<UserEntity> destinatarios = usuarioRepository.findByRolesName(DESTINATARIOS);
        if (destinatarios.isEmpty()) {
            log.warn("Existem tarefas vencidos, mas o " + "sistema não encontrou destinatários.");
            return;
        }
        mailer.avisarSobreTarefasVencidas(vencidos, destinatarios);
        log.info("Envio de e-mail de aviso concluído.");
    }
}
