package com.leadsponge.IO.models.negociacao;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.models.historicoEstagioNegociacao.HistEstagioNegociacao;
import com.leadsponge.IO.models.motivoPerda.MotivoPerda;
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
	
	@Column(name = "dataPrevistaEncerramento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataPrevistaEncerramento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EstatusNegociacao estatus;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "valor_mensal")
	private BigDecimal valorMensal;

	@Column(name = "valor_unico")
	private BigDecimal valorUnico;

	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@NotNull
	@JoinColumn(name = "campanha_id")
	private Campanha campanha;

	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@NotNull
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@NotNull
	@JoinColumn(name = "estagio_id")
	private EstagioNegociacao estagio;

	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@NotNull
	@JoinColumn(name = "fonte_id")
	private FonteNegociacao fonte;

	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@JoinColumn(name = "motivo_perda_negociacao_id")
	private MotivoPerda motivoPerda;

	@OneToOne(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("negociacao")
	@Valid
	private NegociacaoProduto negociacaoProduto;

	@OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("negociacao")
	@Valid
	private List<HistEstagioNegociacao> histEstagioNegociacoes;

	@OneToOne(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("negociacao")
	@Valid
	private Tarefa tarefa;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public EstagioNegociacao getEstagio() {
		return estagio;
	}

	public void setEstagio(EstagioNegociacao estagio) {
		this.estagio = estagio;
	}

	public FonteNegociacao getFonte() {
		return fonte;
	}

	public void setFonte(FonteNegociacao fonte) {
		this.fonte = fonte;
	}

	public MotivoPerda getMotivoPerda() {
		return motivoPerda;
	}

	public void setMotivoPerda(MotivoPerda motivoPerda) {
		this.motivoPerda = motivoPerda;
	}

	public NegociacaoProduto getNegociacaoProduto() {
		return negociacaoProduto;
	}

	public void setNegociacaoProduto(NegociacaoProduto negociacaoProduto) {
		this.negociacaoProduto = negociacaoProduto;
	}

	public List<HistEstagioNegociacao> getHistEstagioNegociacoes() {
		return histEstagioNegociacoes;
	}

	public void setHistEstagioNegociacoes(List<HistEstagioNegociacao> histEstagioNegociacoes) {
		this.histEstagioNegociacoes = histEstagioNegociacoes;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	@JsonIgnore
	public boolean isReceita() {
		return EstatusNegociacao.EMDAMENTO.equals(estatus);
	}

	public EstatusNegociacao getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusNegociacao estatus) {
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
