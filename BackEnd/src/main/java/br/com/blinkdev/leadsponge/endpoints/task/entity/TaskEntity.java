package br.com.blinkdev.leadsponge.endpoints.task.entity;

import br.com.blinkdev.leadsponge.endpoints.View;
import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endpoints.task.enumeration.TypeTask;
import br.com.blinkdev.leadsponge.endpoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private String assunto;

    @Size(max = 255)
    private String descricao;

    @Column(name = "hora_vencimento")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horaMarcada;

    private Boolean realizda;

    @Column(name = "hora_realizada")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horaRealizada;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeTask tipo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties(value = {"tarefas", "roles", "clientesSeguidos"})
    private UserEntity usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "negociacao_id")
    @JsonIgnoreProperties(value = {"cliente", "tarefa"})
    private NegotiationEntity negociacao;

    public TaskEntity(Long id, @NotNull(message = "{assunto.null}") @Size(min = 4, max = 50, message = "{assunto.size}") String assunto, @Size(max = 255) String descricao, String horaMarcada, Boolean realizda, String horaRealizada, @NotNull TypeTask tipo, @NotNull UserEntity usuario, @NotNull NegotiationEntity negociacao) {
        this.id = id;
        this.assunto = assunto;
        this.descricao = descricao;
//		this.horaMarcada = horaMarcada;
        this.realizda = realizda;
//		this.horaRealizada = horaRealizada;
        this.tipo = tipo;
        this.usuario = usuario;
        this.negociacao = negociacao;
    }

    @JsonIgnore
    public boolean isEmail() {
        return TypeTask.EMAIL.equals(tipo);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaskEntity tarefa = (TaskEntity) o;

        return Objects.equals(id, tarefa.id);
    }

    @Override
    public int hashCode() {
        return 791331939;
    }
}
