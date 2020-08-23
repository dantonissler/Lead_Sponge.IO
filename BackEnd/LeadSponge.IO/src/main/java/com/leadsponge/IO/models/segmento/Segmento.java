package com.leadsponge.IO.models.segmento;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.cliente.Cliente;

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
@Table(name = "segmentos")
@TableGenerator(name = "segmento_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "segmentos", allocationSize = 1, initialValue = 0)
public class Segmento extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Segmento.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "segmento_id")
	private Long id;

	@Size(min = 4, max = 50)
	private String nome;

	@ManyToMany(mappedBy = "segmentos", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("segmentos")
	private List<Cliente> clientes;
}
