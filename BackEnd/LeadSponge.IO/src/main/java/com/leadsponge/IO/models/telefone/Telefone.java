package com.leadsponge.IO.models.telefone;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.contato.Contato;

import lombok.Data;

@Entity
@Data
@Table(name = "telefones")
@TableGenerator(name = "telefone_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "telefones", allocationSize = 1, initialValue = 0)
public class Telefone extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Telefone.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "telefone_id")
	private Long id;

	@Column(name = "numero")
	@Size(max = 20)
	private String numero;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference("telefoneContato")
	@JoinColumn(name = "contato_id")
	private Contato contatoTelefone;

	@JsonIgnore
	public boolean isComercial() {
		return TipoTelefone.COMERCIAL.equals(tipo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public Contato getContato() {
		return contatoTelefone;
	}

	public void setContato(Contato contatoTelefone) {
		this.contatoTelefone = contatoTelefone;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}