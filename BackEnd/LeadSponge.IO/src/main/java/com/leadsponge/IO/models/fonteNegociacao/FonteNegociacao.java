package com.leadsponge.IO.models.fonteNegociacao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;

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
@Table(name = "fonte_negociacao")
@TableGenerator(name = "fonte_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "fonte_negociacao", allocationSize = 1, initialValue = 0)
public class FonteNegociacao extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.EstagioNegociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fonte_negociacao_id")
	private Long id;

	@Size(min = 4, max = 50)
	private String nome;

	@JsonIgnoreProperties("fonte")
	@Valid
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

	public FonteNegociacao(@Size(min = 4, max = 50) String nome) {
		super();
		this.nome = nome;
	}
}
