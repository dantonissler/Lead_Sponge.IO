package br.com.blinkdev.leadsponge.models.negociacao;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.campanha.CampanhaEntity;
import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.models.historicoEstagioNegociacao.HistEstagioNegociacao;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negociacoes")
@TableGenerator(name = "negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "negociacoes", allocationSize = 1)
public class Negociacao extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    public Negociacao(Long id) {
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataPrevistaEncerramento;

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
    private CampanhaEntity campanha;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties(value = {"negociacoes", "tarefas", "contato", "segmentos", "seguidores",
            "responsavel"}, allowSetters = true)
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
    @JsonIgnoreProperties(value = {"negociacao"})
    @ToString.Exclude
    private List<NegociacaoProduto> negociacaoProdutos;

    @OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("negociacao")
    @ToString.Exclude
    private List<HistEstagioNegociacao> histEstagioNegociacoes;

    @OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("negociacao")
    @ToString.Exclude
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
    public int hashCode() {
        return 117961993;
    }

}
