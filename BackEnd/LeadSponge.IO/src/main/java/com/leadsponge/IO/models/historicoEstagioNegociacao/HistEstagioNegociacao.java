package com.leadsponge.IO.models.historicoEstagioNegociacao;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@NotNull
	@Column(name = "data_mudanca")
	private LocalDate dataMudanca;

	@Column(name = "data_venda")
	private LocalDate dataVenda;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "negociacao_id")
	@JsonBackReference("histEstagioNegociacaoNegociacao")
	private Negociacao negociacaoHistEstagioNegociacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataMudanca() {
		return dataMudanca;
	}

	public void setDataMudanca(LocalDate dataMudanca) {
		this.dataMudanca = dataMudanca;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Negociacao getNegociacao() {
		return negociacaoHistEstagioNegociacao;
	}

	public void setNegociacao(Negociacao negociacaoHistEstagioNegociacao) {
		this.negociacaoHistEstagioNegociacao = negociacaoHistEstagioNegociacao;
	}

}
