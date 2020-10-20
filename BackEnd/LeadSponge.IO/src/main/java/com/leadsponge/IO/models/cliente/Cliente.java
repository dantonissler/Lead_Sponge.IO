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
import javax.validation.constraints.NotNull;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@TableGenerator(name = "cliente_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "clientes", allocationSize = 1, initialValue = 0)
public class Cliente extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Cliente.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "cliente_id")
	private Long id;

	@Column(name = "nome")
	@NotNull(message = "{nome.null}")
	@Size(min = 4, max = 50, message = "{nome.size}")
	private String nome;

	@Size(max = 1024, message = "{descricao.max}")
	private String url;

	@Size(max = 1024, message = "{descricao.max}")
	private String resumo;

	@Valid
	@JsonIgnoreProperties("cliente")
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Contato> contato = new ArrayList<>();

	@Valid
	@JsonIgnoreProperties(value = { "cliente", "clientes", "clientesSeguidos", "roles", "tarefas" })
	@OneToMany(mappedBy = "cliente")
	private List<Negociacao> negociacoes;

	@Valid
	@ManyToMany
	@JsonIgnoreProperties("clientes")
	@JoinTable(name = "segmentos_clientes", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segmento_id", referencedColumnName = "id"))
	private List<Segmento> segmentos = new ArrayList<>();

	@Valid
	@ManyToMany
	@JsonIgnoreProperties(value = { "clientes", "clientesSeguidos", "roles", "tarefas" })
	@JoinTable(name = "seguidores_clientesSeguidos", joinColumns = @JoinColumn(name = "clientesSeguidos_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "seguidor_id", referencedColumnName = "id"))
	private List<Usuario> seguidores = new ArrayList<>();

	@Valid
	@JsonIgnoreProperties(value = { "clientes", "clientesSeguidos", "roles", "tarefas" })
	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Usuario responsavel;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
