package com.leadsponge.IO.models.fonteNegociacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "fonte_negociacao")
@TableGenerator(name = "fonte_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "fonte_negociacao", allocationSize = 1, initialValue = 0)
public class FonteNegociacao extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.EstagioNegociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fonte_negociacao_id")
	private Long id;

	@Size(min = 4, max = 50)
	private String nome;

	@OneToOne
	@JoinColumn(name = "negociacao_id")
	private Negociacao negociacao;

	public FonteNegociacao() {
		// TODO Auto-generated constructor stub
	}

	public FonteNegociacao(String nome) {
		super();
		this.nome = nome;
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
		FonteNegociacao other = (FonteNegociacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
