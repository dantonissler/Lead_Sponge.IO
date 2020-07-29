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

import lombok.Data;

@Entity
@Data
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataMudanca() {
		return dataMudanca;
	}

	public void setDataMudanca() {
		this.dataMudanca = getUpdatedAt();
	}

	public Negociacao getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(Negociacao negociacao) {
		this.negociacao = negociacao;
	}

}
