package br.com.blinkdev.leadsponge.endpoints.campaign.entity;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CampaignEntity.class)
public abstract class CampaignEntity_ {
    public static volatile SingularAttribute<CampaignEntity, Long> id;
    public static volatile SingularAttribute<CampaignEntity, String> nome;
    public static volatile SingularAttribute<CampaignEntity, String> descricao;
    public static volatile ListAttribute<CampaignEntity, NegotiationEntity> negociacoes;
}
