package br.com.blinkdev.leadsponge.end_points.task.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.task.enumeration.KindTask;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@TableGenerator(name = "task_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "task", allocationSize = 1)
public class TaskEntity extends UserDateAudit {

    @Id
    @JsonView(View.Tarefa.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "task_id")
    private Long id;

    @NotNull(message = "{assunto.null}")
    @Size(min = 4, max = 50, message = "{assunto.size}")
    private String subject;

    @Size(max = 255)
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime appointment;

    private Boolean done;

    @Column(name = "time_performed")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timePerformed;

    @NotNull
    @Enumerated(EnumType.STRING)
    private KindTask kind;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties(value = {"tarefas", "roles", "clientesSeguidos"})
    private UserEntity user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "negociacao_id")
    @JsonIgnoreProperties(value = {"cliente", "tarefa"})
    private NegotiationEntity negotiation;

    @JsonIgnore
    public boolean isEmail() {
        return KindTask.EMAIL.equals(kind);
    }

}
