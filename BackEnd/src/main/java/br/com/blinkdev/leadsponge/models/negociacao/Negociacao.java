package br.com.blinkdev.leadsponge.models.negociacao;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.blinkdev.leadsponge.models.campanha.Campanha;
import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.models.historicoEstagioNegociacao.HistEstagioNegociacao;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negociacoes")
@TableGenerator(name = "negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "negociacoes", allocationSize = 1)
public class Negociacao extends UserDateAudit {

	public Negociacao(Long id){
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@JsonView(View.Negociacao.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "negociacao_id")
	private Long id;

	@Column(name = "nome")
	@Size(min = 4, max = 50, message = "{nome.size}")
	@NotNull(message = "{nome.null}")
	private String nome;

	@Column(name = "avaliacao")
	@NotNull
	private Integer avaliacao;

	@Column(name = "data_prevista_encerramento")
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

	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@JoinColumn(name = "campanha_id")
	private Campanha campanha;

	@NotNull
	@ManyToOne
	@JsonIgnoreProperties(value = { "negociacoes", "tarefas", "contato", "segmentos", "seguidores",
			"responsavel" }, allowSetters = true)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@JoinColumn(name = "estagio_id")
	private EstagioNegociacao estagio;

	@NotNull
	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@JoinColumn(name = "fonte_id")
	private FonteNegociacao fonte;

	@ManyToOne
	@JsonIgnoreProperties("negociacoes")
	@JoinColumn(name = "motivo_perda_negociacao_id")
	private MotivoPerda motivoPerda;

	@OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "negociacao" })
	private List<NegociacaoProduto> negociacaoProdutos;

	@OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("negociacao")
	private List<HistEstagioNegociacao> histEstagioNegociacoes;

	@OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("negociacao")
	private List<Tarefa> tarefas;

	@JsonIgnore
	public boolean isReceita() {
		return EstatusNegociacao.EMANDAMENTO.equals(estatus);
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
