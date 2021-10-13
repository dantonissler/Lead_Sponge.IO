package br.com.blinkdev.leadsponge.end_points.negotiation.entity;

import br.com.blinkdev.leadsponge.end_points.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.end_points.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity.HistoryNegotiationStyleEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegotiationEntity.class)
public abstract class NegotiationEntity_ {
    public static volatile SingularAttribute<NegotiationEntity, Long> id;
    public static volatile SingularAttribute<NegotiationEntity, String> name;
    public static volatile SingularAttribute<NegotiationEntity, StatusNegotiation> status;
    public static volatile SingularAttribute<NegotiationEntity, ReasonForLossEntity> reasonForLoss;
    public static volatile SingularAttribute<NegotiationEntity, CampaignEntity> campaign;
    public static volatile ListAttribute<NegotiationEntity, TradeProductsEntity> tradeProducts;
    public static volatile SingularAttribute<NegotiationEntity, Integer> evaluation;
    public static volatile SingularAttribute<NegotiationEntity, Date> expectedClosingDate;
    public static volatile SingularAttribute<NegotiationEntity, BigDecimal> amount;
    public static volatile SingularAttribute<NegotiationEntity, BigDecimal> monthlyValue;
    public static volatile SingularAttribute<NegotiationEntity, BigDecimal> singleValue;
    public static volatile SingularAttribute<NegotiationEntity, CustomerEntity> customer;
    public static volatile SingularAttribute<NegotiationEntity, NegotiationStyleEntity> negotiationStyle;
    public static volatile SingularAttribute<NegotiationEntity, NegotiationSourceEntity> negotiationSource;
    public static volatile ListAttribute<NegotiationEntity, HistoryNegotiationStyleEntity> historyNegotiationStyle;
    public static volatile ListAttribute<NegotiationEntity, TaskEntity> task;
}
