package com.leadsponge.IO.models.estagioNegociacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;
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
@Table(name = "estagio_negociacao")
@TableGenerator(name = "estagio_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "estagio_negociacao", allocationSize = 1)
public class EstagioNegociacao extends UserDateAudit {

	private static final long serialVersionUID = 1L;

	public EstagioNegociacao(@NotNull @Size(min = 4, max = 50) String nome, @NotNull @Size(max = 10) String apelido, @NotNull Integer posicao) {
		this.nome = nome;
		this.apelido = apelido;
		this.posicao = posicao;
	}

	@Id
	@Column(name = "id")
	@JsonView(View.EstagioNegociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "estagio_negociacao_id")
	private Long id;

	@Column(name = "nome")
	@NotNull(message = "{nome.null}")
	@Size(min = 4, max = 50, message = "{nome.size}")
	private String nome;

	@Column(name = "apelido")
	@NotNull(message = "{apelido.null}")
	@Size(max = 10, message = "{apelido.size}")
	private String apelido;

	@Column(name = "posicao")
	@NotNull(message = "{posicao.null}")
	private Integer posicao;

	@JsonIgnoreProperties("estagio")
	@OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Negociacao> negociacoes;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstagioNegociacao other = (EstagioNegociacao) obj;
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
