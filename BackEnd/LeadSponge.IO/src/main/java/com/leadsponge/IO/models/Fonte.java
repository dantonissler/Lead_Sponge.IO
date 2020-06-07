package com.leadsponge.IO.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.view.View;

import lombok.Data;

@Entity
@Data
@Table(name = "fontes")
@TableGenerator(name = "fonte_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "fontes", allocationSize = 1, initialValue = 0)
public class Fonte extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Fonte.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fonte_id")
	private Long id;

	@Size(min = 4, max = 50)
	private String nome;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fonte other = (Fonte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
