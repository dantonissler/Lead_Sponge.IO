package com.leadsponge.IO.models.email;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.contato.Contato;

import lombok.Data;

@Entity
@Data
@Table(name = "emails")
@TableGenerator(name = "email_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "emails", allocationSize = 1, initialValue = 0)
public class Email extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Email.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "email_id")
	private Long id;

	@javax.validation.constraints.Email(message = "{email.not.valid}")
	private String email;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "contato_id")
	@JsonBackReference("emailContato")
	private Contato contatoEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Contato getContato() {
		return contatoEmail;
	}

	public void setContato(Contato contato) {
		this.contatoEmail = contato;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
