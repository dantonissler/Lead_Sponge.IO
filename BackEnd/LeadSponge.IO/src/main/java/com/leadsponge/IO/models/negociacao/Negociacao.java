package com.leadsponge.IO.models.negociacao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.models.historicoEstagioNegociacao.HistEstagioNegociacao;
import com.leadsponge.IO.models.negociacaoProduto.NegociacaoProduto;
import com.leadsponge.IO.models.tarefa.Tarefa;

import lombok.Data;

@Entity
@Data
@Table(name = "negociacoes")
@TableGenerator(name = "negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "negociacoes", allocationSize = 1, initialValue = 0)
public class Negociacao extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Negociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "negociacao_id")
	private Long id;

	@Column(name = "nome")
	@Size(min = 4, max = 50)
	private String nome;

	@Column(name = "avaliacao")
	@NotNull
	private Integer avaliacao;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusNegociacao estatus;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "valor_mensal")
	private BigDecimal valorMensal;

	@Column(name = "valor_unico")
	private BigDecimal valorUnico;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "campanha_id")
	@JsonBackReference("negociacoesCampanha")
	private Campanha campanhaNegociacoes;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	@JsonBackReference("negociacoesCliente")
	private Cliente clienteNegociacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estagio_negociacao_id")
	@JsonBackReference("negociacaoEstagioNegociacao")
	private EstagioNegociacao estagioNegociacaoNegociacao;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fonte_negociacao_id")
	@JsonBackReference("negociacaoFonteNegociacao")
	private FonteNegociacao fonteNegociacaoNegociacao;

	@OneToOne(mappedBy = "negociacaoProdutoNegociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonManagedReference("negociacaoProdutoNegociacao")
	private NegociacaoProduto negociacaoProdutoN;

	@OneToMany(mappedBy = "negociacaoHistEstagioNegociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonManagedReference("negociacaoHistEstagioNegociacao")
	private List<HistEstagioNegociacao> histEstagioNegociacaoNegociacao;

	@OneToOne(mappedBy = "negociacaoTarefa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonManagedReference(value = "negociacaoTarefa")
	private Tarefa tarefaNegociacao;

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

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(BigDecimal valorMensal) {
		this.valorMensal = valorMensal;
	}

	public BigDecimal getValorUnico() {
		return valorUnico;
	}

	public void setValorUnico(BigDecimal valorUnico) {
		this.valorUnico = valorUnico;
	}

	public Campanha getCampanhaNegociacoes() {
		return campanhaNegociacoes;
	}

	public void setCampanhaNegociacoes(Campanha campanhaNegociacoes) {
		this.campanhaNegociacoes = campanhaNegociacoes;
	}

	public Cliente getClienteNegociacao() {
		return clienteNegociacao;
	}

	public void setClienteNegociacao(Cliente clienteNegociacao) {
		this.clienteNegociacao = clienteNegociacao;
	}

	public EstagioNegociacao getEstagioNegociacaoNegociacao() {
		return estagioNegociacaoNegociacao;
	}

	public void setEstagioNegociacaoNegociacao(EstagioNegociacao estagioNegociacaoNegociacao) {
		this.estagioNegociacaoNegociacao = estagioNegociacaoNegociacao;
	}

	public FonteNegociacao getFonteNegociacaoNegociacao() {
		return fonteNegociacaoNegociacao;
	}

	public void setFonteNegociacaoNegociacao(FonteNegociacao fonteNegociacaoNegociacao) {
		this.fonteNegociacaoNegociacao = fonteNegociacaoNegociacao;
	}

	public NegociacaoProduto getNegociacaoProdutoN() {
		return negociacaoProdutoN;
	}

	public void setNegociacaoProdutoN(NegociacaoProduto negociacaoProdutoN) {
		this.negociacaoProdutoN = negociacaoProdutoN;
	}

	public List<HistEstagioNegociacao> getHistEstagioNegociacaoNegociacao() {
		return histEstagioNegociacaoNegociacao;
	}

	public void setHistEstagioNegociacaoNegociacao(List<HistEstagioNegociacao> histEstagioNegociacaoNegociacao) {
		this.histEstagioNegociacaoNegociacao = histEstagioNegociacaoNegociacao;
	}

	public Tarefa getTarefaNegociacao() {
		return tarefaNegociacao;
	}

	public void setTarefaNegociacao(Tarefa tarefaNegociacao) {
		this.tarefaNegociacao = tarefaNegociacao;
	}

	@JsonIgnore
	public boolean isReceita() {
		return StatusNegociacao.ANDAMENTO.equals(estatus);
	}

	public StatusNegociacao getEstatus() {
		return estatus;
	}

	public void setEstatus(StatusNegociacao estatus) {
		this.estatus = estatus;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Negociacao other = (Negociacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
