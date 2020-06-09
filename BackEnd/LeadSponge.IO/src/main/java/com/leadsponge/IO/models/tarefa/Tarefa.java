package com.leadsponge.IO.models.tarefa;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.usuario.Usuario;

import lombok.Data;

@Entity
@Data
@Table(name = "tarefas")
@TableGenerator(name = "tarefa_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "tarefas", allocationSize = 1, initialValue = 0)
public class Tarefa extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Tarefa.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tarefa_id")
	private Long id;

	@Size(min = 4, max = 50)
	private String assunto;

	@Size(max = 255)
	private String descricao;

	@Column(name = "hora_vencimento")
	private LocalDateTime horaMarcada;

	@Enumerated(EnumType.STRING)
	private TipoTarefa tipo;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonBackReference("tarefaUsuario")
	private Usuario usuarioTarefa;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	@JsonBackReference("tarefasCliente")
	private Cliente clienteTarefa;

	@OneToOne
	@JoinColumn(name = "negociacao_id", nullable = false)
	@JsonBackReference("tarefaNegociacao")
	private Negociacao negociacaoTarefa;

	@JsonIgnore
	public boolean isEmail() {
		return TipoTarefa.EMAIL.equals(tipo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getHoraMarcada() {
		return horaMarcada;
	}

	public void setHoraMarcada(LocalDateTime horaMarcada) {
		this.horaMarcada = horaMarcada;
	}

	public TipoTarefa getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarefa tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuarioTarefa() {
		return usuarioTarefa;
	}

	public void setUsuarioTarefa(Usuario usuarioTarefa) {
		this.usuarioTarefa = usuarioTarefa;
	}

	public Cliente getClienteTarefa() {
		return clienteTarefa;
	}

	public void setClienteTarefa(Cliente clienteTarefa) {
		this.clienteTarefa = clienteTarefa;
	}

	public Negociacao getNegociacaoTarefa() {
		return negociacaoTarefa;
	}

	public void setNegociacaoTarefa(Negociacao negociacaoTarefa) {
		this.negociacaoTarefa = negociacaoTarefa;
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
}
