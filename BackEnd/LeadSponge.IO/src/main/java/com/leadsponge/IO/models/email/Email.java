package com.leadsponge.IO.models.email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.contato.Contato;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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

	@ManyToOne
	@JoinColumn(name = "contato_id")
	private Contato contato;
}
