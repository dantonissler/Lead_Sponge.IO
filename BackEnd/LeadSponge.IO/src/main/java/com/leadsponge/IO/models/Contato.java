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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leadsponge.IO.models.audit.UserDateAudit;

import lombok.Data;

@Entity
@Data
@Table(name = "contatos")
@TableGenerator(name = "contato_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "contatos", allocationSize = 1, initialValue = 0)
public class Contato extends UserDateAudit {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "contato_id")
	private Long id;

	@Size(min = 4, max = 50, message = "{nome.size}")
	private String nome;

	@Size(max = 50, message = "{cargo.size}")
	private String cargo;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("contato")
	private List<Telefone> telefone;

	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("contato")
	private List<Email> email;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

	public List<Email> getEmail() {
		return email;
	}

	public void setEmail(List<Email> email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
