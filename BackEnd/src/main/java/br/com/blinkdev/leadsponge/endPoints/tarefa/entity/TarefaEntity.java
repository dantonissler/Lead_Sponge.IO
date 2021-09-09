package br.com.blinkdev.leadsponge.endPoints.tarefa.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.enumeration.TipoTarefa;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
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
@Table(name = "tarefas")
@TableGenerator(name = "tarefa_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "tarefas", allocationSize = 1)
public class TarefaEntity extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Tarefa.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tarefa_id")
	private Long id;

	@Column(name = "assunto")
	@NotNull(message = "{assunto.null}")
	@Size(min = 4, max = 50, message = "{assunto.size}")
	private String assunto;

	@Size(max = 255)
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "hora_vencimento")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime horaMarcada;

	@Column(name = "realizada")
	private Boolean realizda;

	@Column(name = "hora_realizada")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime horaRealizada;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoTarefa tipo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties(value = {"tarefas", "roles", "clientesSeguidos"})
	private UserEntity usuario;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "negociacao_id")
	@JsonIgnoreProperties(value = {"cliente", "tarefa"})
	private NegociacaoEntity negociacao;

	@JsonIgnore
	public boolean isEmail() {
		return TipoTarefa.EMAIL.equals(tipo);
	}

	public TarefaEntity(Long id, @NotNull(message = "{assunto.null}") @Size(min = 4, max = 50, message = "{assunto.size}") String assunto, @Size(max = 255) String descricao, String horaMarcada, Boolean realizda, String horaRealizada, @NotNull TipoTarefa tipo, @NotNull UserEntity usuario, @NotNull NegociacaoEntity negociacao) {
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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		TarefaEntity tarefa = (TarefaEntity) o;

		return Objects.equals(id, tarefa.id);
	}

	@Override
	public int hashCode() {
		return 791331939;
	}
}
