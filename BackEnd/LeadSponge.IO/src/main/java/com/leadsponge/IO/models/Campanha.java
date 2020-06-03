package com.leadsponge.IO.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "campanha")
@TableGenerator(name = "campanha_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "campanha", allocationSize = 1, initialValue = 0)
public class Campanha {

	private @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.TABLE, generator = "campanha_id") Long id;

	private @Size(min = 4, max = 50, message = "{nome.size}") String nome;

	private @OneToMany @JoinColumn(name = "campanha_id") List<Oportunidade> oportunidade;

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

	public List<Oportunidade> getOportunidade() {
		return oportunidade;
	}

	public void setOportunidade(List<Oportunidade> oportunidade) {
		this.oportunidade = oportunidade;
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
