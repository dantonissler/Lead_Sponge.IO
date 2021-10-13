package br.com.blinkdev.leadsponge.end_points.negotiation.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.end_points.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negotiations")
@TableGenerator(name = "negociacao_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "negotiations", allocationSize = 1)
public class NegotiationEntity extends UserDateAudit {

    @Id
    @JsonView(View.Negociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "negociacao_id")
    private Long id;

    @Size(min = 4, max = 50, message = "{nome.size}")
    @NotNull(message = "{nome.null}")
    private String name;

    @NotNull
    private Integer evaluation;

    @Column(name = "expected_closing_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime expectedClosingDate;

    private BigDecimal amount;

    @Column(name = "monthly_value")
    private BigDecimal monthlyValue;

    @Column(name = "single_value")
    private BigDecimal singleValue;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusNegotiation status;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties(value = {"negociacoes", "tarefas", "contato", "segmentos", "seguidores", "responsavel"}, allowSetters = true)
    @JoinColumn(name = "cliente_id")
    private CustomerEntity customer;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "campanha_id")
    private CampaignEntity campaign;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "estagio_id")
    private NegotiationStyleEntity negotiationStyle;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "fonte_id")
    private NegotiationSourceEntity negotiationSource;

    @ManyToOne
    @JsonIgnoreProperties("negociacoes")
    @JoinColumn(name = "motivo_perda_negociacao_id")
    private ReasonForLossEntity reasonForLoss;

    @OneToMany(targetEntity = TradeProductsEntity.class, mappedBy = "negotiation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TradeProductsEntity> tradeProducts;

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
