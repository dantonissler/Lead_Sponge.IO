package br.com.blinkdev.leadsponge.endPoints.negociacao.entity;

import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.historicoEstagioNegociacao.entity.HistEstagioNegociacao;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration.EstatusNegociacao;
import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
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
public class NegociacaoEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    public NegociacaoEntity(Long id) {
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
    private CampaignEntity campanha;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties(value = {"negociacoes", "tarefas", "contato", "segmentos", "seguidores",
            "responsavel"}, allowSetters = true)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "estagio_id")
    private EstagioNegociacaoEntity estagio;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "fonte_id")
    private FonteNegociacaoEntity fonte;

    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "motivo_perda_negociacao_id")
    private MotivoPerdaEntity motivoPerda;

    @OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"negociacao"})
    @ToString.Exclude
    private List<NegociacaoProdutoEntity> negociacaoProdutos;

    @OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("negociacao")
    @ToString.Exclude
    private List<HistEstagioNegociacao> histEstagioNegociacoes;

    @OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("negociacao")
    @ToString.Exclude
    private List<TarefaEntity> tarefas;

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
        NegociacaoEntity other = (NegociacaoEntity) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 117961993;
    }

}
