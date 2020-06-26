package com.leadsponge.IO.models.estagioNegociacao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;

import lombok.Data;

@Entity
@Data
@Table(name = "estagio_negociacao")
@TableGenerator(name = "estagio_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "estagio_negociacao", allocationSize = 1, initialValue = 0)
public class EstagioNegociacao extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.EstagioNegociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "estagio_negociacao_id")
	private Long id;

	@Column(name = "nome")
	@Size(min = 4, max = 50)
	private String nome;

	@Column(name = "apelido")
	@Size(min = 1, max = 10)
	private String apelido;

	@OneToOne(mappedBy = "estagioNegociacao", cascade = CascadeType.ALL)
	private Negociacao negociacao;

	public EstagioNegociacao() {
		// TODO Auto-generated constructor stub
	}

	public EstagioNegociacao(String nome, String apelido) {
		super();
		this.nome = nome;
		this.apelido = apelido;
	}

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

	public String getApelido() {
		return apelido.toUpperCase();
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Negociacao getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(Negociacao negociacao) {
		this.negociacao = negociacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstagioNegociacao other = (EstagioNegociacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
