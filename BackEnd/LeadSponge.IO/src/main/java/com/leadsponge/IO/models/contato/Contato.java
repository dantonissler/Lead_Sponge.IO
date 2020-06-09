package com.leadsponge.IO.models.contato;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.models.telefone.Telefone;

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	@JsonBackReference("contatosCliente")
	private Cliente clienteContato;

	@OneToMany(mappedBy = "contatoTelefone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonManagedReference("contatoTelefone")
	private List<Telefone> telefoneContato = new ArrayList<>();

	@OneToMany(mappedBy = "contatoEmail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonManagedReference("contatoEmail")
	private List<Email> emailContato = new ArrayList<>();

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
		return clienteContato;
	}

	public void setCliente(Cliente clienteContato) {
		this.clienteContato = clienteContato;
	}

	public List<Telefone> getTelefone() {
		return telefoneContato;
	}

	public void setTelefone(List<Telefone> telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public List<Email> getEmail() {
		return emailContato;
	}

	public void setEmail(List<Email> emailContato) {
		this.emailContato = emailContato;
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
