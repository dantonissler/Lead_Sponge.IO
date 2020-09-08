package com.leadsponge.IO.models.tarefa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.usuario.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarefas")
@TableGenerator(name = "tarefa_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "tarefas", allocationSize = 1, initialValue = 0)
public class Tarefa extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Tarefa.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tarefa_id")
	private Long id;

	@NotNull
	@Size(min = 4, max = 50)
	private String assunto;

	@Size(max = 255)
	private String descricao;

	@Column(name = "hora_vencimento")
	private Date horaMarcada;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoTarefa tipo;

	@JsonIgnoreProperties(value = { "tarefas", "roles", "clientesSeguidos" })
	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@JsonIgnoreProperties(value = { "cliente", "tarefa" })
	@NotNull
	@ManyToOne
	@JoinColumn(name = "negociacao_id")
	private Negociacao negociacao;

	@JsonIgnore
	public boolean isEmail() {
		return TipoTarefa.EMAIL.equals(tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
