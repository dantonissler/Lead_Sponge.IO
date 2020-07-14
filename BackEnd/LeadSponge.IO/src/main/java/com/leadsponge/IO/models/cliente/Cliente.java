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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

	@JsonIgnoreProperties("cliente")
	@Valid
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> contato = new ArrayList<>();

	@JsonIgnoreProperties("cliente")
	@Valid
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tarefa> tarefas = new ArrayList<>();

	@JsonIgnoreProperties("cliente")
	@Valid
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Negociacao> negociacoes;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("clientes")
	@JoinTable(name = "segmentos_clientes", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segmento_id", referencedColumnName = "id"))
	private List<Segmento> segmentos = new ArrayList<>();

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

	public List<Contato> getContato() {
		return contato;
	}

	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public void setNegociacoes(List<Negociacao> negociacoes) {
		this.negociacoes = negociacoes;
	}

	public List<Segmento> getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(List<Segmento> segmentos) {
		this.segmentos = segmentos;
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
