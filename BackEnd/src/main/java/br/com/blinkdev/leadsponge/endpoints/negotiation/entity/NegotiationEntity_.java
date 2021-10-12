package br.com.blinkdev.leadsponge.endpoints.negotiation.entity;

import br.com.blinkdev.leadsponge.endpoints.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endpoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.enumeration.StatusNegotiation;
import br.com.blinkdev.leadsponge.endpoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endpoints.task.entity.TaskEntity;
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
    public static volatile SingularAttribute<NegotiationEntity, String> nome;
    public static volatile SingularAttribute<NegotiationEntity, StatusNegotiation> estatus;
    public static volatile SingularAttribute<NegotiationEntity, ReasonForLossEntity> motivoPerda;
    public static volatile SingularAttribute<NegotiationEntity, CampaignEntity> campanha;
    public static volatile ListAttribute<NegotiationEntity, TradeProductsEntity> TradeProducts;
    public static volatile SingularAttribute<NegotiationEntity, Integer> avaliacao;
    public static volatile SingularAttribute<NegotiationEntity, Date> dataPrevistaEncerramento;
    public static volatile SingularAttribute<NegotiationEntity, BigDecimal> valorTotal;
    public static volatile SingularAttribute<NegotiationEntity, BigDecimal> valorMensal;
    public static volatile SingularAttribute<NegotiationEntity, BigDecimal> valorUnico;
    public static volatile SingularAttribute<NegotiationEntity, CustomerEntity> cliente;
    public static volatile SingularAttribute<NegotiationEntity, NegotiationStyleEntity> estagio;
    public static volatile SingularAttribute<NegotiationEntity, NegotiationSourceEntity> fonte;
    public static volatile ListAttribute<NegotiationEntity, HistoryNegotiationStyleEntity> histEstagioNegociacoes;
    public static volatile ListAttribute<NegotiationEntity, TaskEntity> tarefas;
}
