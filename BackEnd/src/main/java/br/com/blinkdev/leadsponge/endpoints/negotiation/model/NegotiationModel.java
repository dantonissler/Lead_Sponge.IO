package br.com.blinkdev.leadsponge.endpoints.negotiation.model;

import br.com.blinkdev.leadsponge.endpoints.campaign.model.CampaignModel;
import br.com.blinkdev.leadsponge.endpoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endpoints.negotiationSource.model.NegotiationSourceModel;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.model.NegotiationStyleModel;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.model.ReasonForLossModel;
import br.com.blinkdev.leadsponge.endpoints.task.model.TaskModel;
import br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity.HistoryNegotiationStyleEntity;
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
    private String nome;
    private Integer avaliacao;
    private LocalDateTime dataPrevistaEncerramento;
    private CustomerEntity cliente;
    private BigDecimal valorTotal;
    private BigDecimal valorMensal;
    private BigDecimal valorUnico;
    private StatusNegotiation estatus;

    private CampaignModel campanha;

    private NegotiationStyleModel estagio;

    private NegotiationSourceModel fonte;

    private ReasonForLossModel motivoPerda;

    private List<TradeProductsModel> negociacaoProdutos;

    private List<HistoryNegotiationStyleEntity> histEstagioNegociacoes;

    private List<TaskModel> tarefas;
}
