package com.leadsponge.IO.models.telefone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@ManyToOne
	@JoinColumn(name = "contato_id")
	private Contato contato;

	@JsonIgnore
	public boolean isComercial() {
		return TipoTelefone.COMERCIAL.equals(tipo);
	}
}