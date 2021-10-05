package br.com.blinkdev.leadsponge.endPoints.negotiation.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity.HistoryNegotiationStyleEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negotiations")
@TableGenerator(name = "negociacao_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "negotiations", allocationSize = 1)
public class NegotiationEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "valor_mensal")
    private BigDecimal valorMensal;

    @Column(name = "valor_unico")
    private BigDecimal valorUnico;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusNegotiation estatus;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties(value = {"negociacoes", "tarefas", "contato", "segmentos", "seguidores", "responsavel"}, allowSetters = true)
    @JoinColumn(name = "cliente_id")
    private CustomerEntity cliente;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "campanha_id")
    private CampaignEntity campanha;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "estagio_id")
    private NegotiationStyleEntity estagio;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "fonte_id")
    private NegotiationSourceEntity fonte;

    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "motivo_perda_negociacao_id")
    private ReasonForLossEntity motivoPerda;

    @OneToMany(targetEntity = TradeProductsEntity.class, mappedBy = "negotiation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TradeProductsEntity> TradeProducts = new ArrayList<>();

    @OneToMany(targetEntity = HistoryNegotiationStyleEntity.class, mappedBy = "negotiation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoryNegotiationStyleEntity> histEstagioNegociacoes;

    @OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("negociacao")
    private List<TaskEntity> tarefas;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NegotiationEntity other = (NegotiationEntity) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 117961993;
    }

}
