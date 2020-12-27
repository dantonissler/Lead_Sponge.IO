package com.leadsponge.IO.models.historicoEstagioNegociacao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hist_estagio_negociacao")
@TableGenerator(name = "hist_estagio_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "hist_estagio_negociacao", allocationSize = 1, initialValue = 0)
public class HistEstagioNegociacao extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.HistEstagioNegociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "hist_estagio_negociacao_id")
	private Long id;

	@NotNull
	@Column(name = "id_estagio")
	private String idEstagio;

	@NotNull
	@Column(name = "apelido")
	private String apelido;

	@NotNull
	@Column(name = "data_mudanca")
	private Date dataMudanca;

	@ManyToOne
	@JoinColumn(name = "negociacao_id")
	private Negociacao negociacao;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistEstagioNegociacao other = (HistEstagioNegociacao) obj;
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
