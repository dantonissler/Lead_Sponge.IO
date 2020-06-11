package com.leadsponge.IO.models.segmento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.cliente.Cliente;

import lombok.Data;

@Entity
@Data
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

	@OneToMany(mappedBy = "segmentoCliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cliente> clienteSegmento = new ArrayList<>();

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

	public List<Cliente> getClientes() {
		return clienteSegmento;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clienteSegmento = clientes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segmento other = (Segmento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
