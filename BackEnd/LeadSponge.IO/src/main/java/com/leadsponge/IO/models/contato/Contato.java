package com.leadsponge.IO.models.contato;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.models.telefone.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contatos")
@TableGenerator(name = "contato_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "contatos", allocationSize = 1)
public class Contato extends UserDateAudit {

	public Contato(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "contato_id")
	private Long id;

	@Column(name = "nome")
	@NotNull(message = "{nome.null}")
	@Size(min = 4, max = 50, message = "{nome.size}")
	private String nome;

	@Size(max = 50, message = "{cargo.size}")
	private String cargo;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@JsonIgnoreProperties("contato")
	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefone;

	@JsonIgnoreProperties("contato")
	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Email> email;

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
			return other.id == null;
		} else return id.equals(other.id);
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
