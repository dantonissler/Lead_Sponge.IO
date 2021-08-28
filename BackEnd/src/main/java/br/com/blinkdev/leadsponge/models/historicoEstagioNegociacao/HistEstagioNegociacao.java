package br.com.blinkdev.leadsponge.models.historicoEstagioNegociacao;

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

import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hist_estagio_negociacao")
@TableGenerator(name = "hist_estagio_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "hist_estagio_negociacao", allocationSize = 1)
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
