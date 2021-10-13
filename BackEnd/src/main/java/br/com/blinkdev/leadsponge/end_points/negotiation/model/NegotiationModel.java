package br.com.blinkdev.leadsponge.end_points.negotiation.model;

import br.com.blinkdev.leadsponge.end_points.campaign.model.CampaignModel;
import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.model.NegotiationSourceModel;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.model.NegotiationStyleModel;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.model.ReasonForLossModel;
import br.com.blinkdev.leadsponge.end_points.task.model.TaskModel;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.model.TradeProductsModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "negotiations", itemRelation = "negotiation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NegotiationModel extends RepresentationModel<NegotiationModel> {

    private Long id;
    private String name;
    private Integer evaluation;
    private LocalDateTime expectedClosingDate;
    private BigDecimal amount;
    private BigDecimal monthlyValue;
    private BigDecimal singleValue;
    private StatusNegotiation statuss;

    private CustomerEntity custome;

    private CampaignModel campaign;

    private NegotiationStyleModel negotiationStyle;

    private NegotiationSourceModel negotiationSource;

    private ReasonForLossModel reasonForLoss;

    private List<TradeProductsModel> tradeProducts;

    private List<TaskModel> tasks;
}
