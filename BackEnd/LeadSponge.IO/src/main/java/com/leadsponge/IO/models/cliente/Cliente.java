package com.leadsponge.IO.models.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.models.usuario.Usuario;

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
@Table(name = "clientes")
@TableGenerator(name = "cliente_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "clientes", allocationSize = 1, initialValue = 0)
public class Cliente extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Cliente.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "cliente_id")
	private Long id;

	@Size(min = 4, max = 50)
	private String nome;

	@Size(max = 1000)
	private String url;

	@Size(max = 255)
	private String resumo;

	@JsonIgnoreProperties("cliente")
	@Valid
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Contato> contato = new ArrayList<>();

	@JsonIgnoreProperties(value = { "cliente", "clientes", "clientesSeguidos", "roles", "tarefas" })
	@OneToMany(mappedBy = "cliente")
	private List<Negociacao> negociacoes;

	@ManyToMany
	@JsonIgnoreProperties("clientes")
	@JoinTable(name = "segmentos_clientes", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segmento_id", referencedColumnName = "id"))
	private List<Segmento> segmentos = new ArrayList<>();

	@ManyToMany
	@JsonIgnoreProperties(value = { "clientes", "clientesSeguidos", "roles", "tarefas" })
	@JoinTable(name = "seguidores_clientesSeguidos", joinColumns = @JoinColumn(name = "clientesSeguidos_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "seguidor_id", referencedColumnName = "id"))
	private List<Usuario> seguidores = new ArrayList<>();

	@JsonIgnoreProperties(value = { "clientes", "clientesSeguidos", "roles", "tarefas" })
	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Usuario responsavel;
}
