package com.leadsponge.IO.models.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.models.tarefa.Tarefa;

import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
@TableGenerator(name = "cliente_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "clientes", allocationSize = 1, initialValue = 0)
public class Cliente extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Cliente.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "cliente_id")
	private Long id;
	@Size(min = 4, max = 50)
	private String nome;

	@Size(max = 1000)
	private String url;

	@Size(max = 255)
	private String resumo;

//	@JsonManagedReference("clienteContato")
	@OneToMany(mappedBy = "clienteContato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Contato> contatosCliente = new ArrayList<>();

//	@JsonManagedReference("clienteTarefa")
	@OneToMany(mappedBy = "clienteTarefa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tarefa> tarefasCliente = new ArrayList<>();

//	@JsonManagedReference("clienteNegociacao")
	@OneToMany(mappedBy = "clienteNegociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Negociacao> negociacoesCliente = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "segmento_id")
	@JsonBackReference("clienteSegmento")
	private Segmento segmento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<Contato> getContatos() {
		return contatosCliente;
	}

	public void setContatos(List<Contato> contatosCliente) {
		this.contatosCliente = contatosCliente;
	}

	public List<Tarefa> getTarefasCliente() {
		return tarefasCliente;
	}

	public void setTarefasCliente(List<Tarefa> tarefasCliente) {
		this.tarefasCliente = tarefasCliente;
	}

	public List<Negociacao> getNegociacoesCliente() {
		return negociacoesCliente;
	}

	public void setNegociacoesCliente(List<Negociacao> negociacoesCliente) {
		this.negociacoesCliente = negociacoesCliente;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
