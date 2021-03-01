package com.leadsponge.IO.models.fonteNegociacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fonte_negociacao")
@TableGenerator(name = "fonte_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "fonte_negociacao", allocationSize = 1)
public class FonteNegociacao extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.FonteNegociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fonte_negociacao_id")
	private Long id;

	@Size(min = 4, max = 50, message = "{nome.size}")
	@NotNull(message = "{nome.null}")
	@Column(name = "nome")
	private String nome;

	@JsonIgnoreProperties("fonte")
	@OneToMany(mappedBy = "fonte", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Negociacao> negociacoes;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FonteNegociacao other = (FonteNegociacao) obj;
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

	public FonteNegociacao(@Size(min = 4, max = 50) String nome) {
		super();
		this.nome = nome;
	}
}
