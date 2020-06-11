package com.leadsponge.IO.models.campanha;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;

import lombok.Data;

@Entity
@Data
@Table(name = "campanhas")
@TableGenerator(name = "campanha_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "campanhas", allocationSize = 1, initialValue = 0)
public class Campanha extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Campanha.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "campanha_id")
	private Long id;

	@Size(min = 4, max = 50, message = "{nome.size}")
	private String nome;

	@JsonBackReference("campanhaNegociacoes")
	@OneToMany(mappedBy = "campanhaNegociacoes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Negociacao> negociacoesCampanha;

	public Campanha() {
		// TODO Auto-generated constructor stub
	}

	public Campanha(String nome) {
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

	public List<Negociacao> Negociacao() {
		return negociacoesCampanha;
	}

	public void setNegociacao(List<Negociacao> negociacoesCampanha) {
		this.negociacoesCampanha = negociacoesCampanha;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campanha other = (Campanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
