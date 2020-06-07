package com.leadsponge.IO.models;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.view.View;

import lombok.Data;

@Entity
@Data
@Table(name = "oportunidades")
@TableGenerator(name = "oportunidade_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "oportunidades", allocationSize = 1, initialValue = 0)
public class Oportunidade extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Oportunidade.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "oportunidade_id")
	private Long id;

	@Column(name = "nome")
	@Size(min = 4, max = 50)
	private String nome;

	@Column(name = "avaliacao")
	@NotNull
	private int avaliacao;

	@Column(name = "quantidadeProdutos")
	private int quantidadeProdutos;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fonte_id")
	private Fonte fonte;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "campanha_id")
	@JsonIgnoreProperties("oportunidade")
	private Campanha campanha;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	@JsonBackReference("oportunidades")
	private Cliente cliente;

	@OneToMany(mappedBy = "oportunidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("oportunidade")
	private List<Produto> produtos;

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

	public Fonte getFonte() {
		return fonte;
	}

	public void setFonte(Fonte fonte) {
		this.fonte = fonte;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oportunidade other = (Oportunidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
