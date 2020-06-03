package com.leadsponge.IO.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import lombok.Data;

@Entity
@Data
@Table(name = "oportunidade")
@TableGenerator(name = "oportunidade_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "oportunidade", allocationSize = 1, initialValue = 0)
public class Oportunidade {

	private @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.TABLE, generator = "oportunidade_id") Long id;

	private @Size(min = 4, max = 50, message = "{nome.size}") String nome;

	private @Size(max = 50, message = "{fonte.size}") String fonte;

	private @ManyToOne @JoinColumn(name = "campanha_id") Campanha campanha;

	private @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cliente_id") @JsonBackReference Cliente cliente;

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

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
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
