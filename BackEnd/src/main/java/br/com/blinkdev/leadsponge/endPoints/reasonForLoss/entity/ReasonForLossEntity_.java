package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReasonForLossEntity.class)
public abstract class ReasonForLossEntity_ {
    public static volatile SingularAttribute<ReasonForLossEntity, Long> id;
    public static volatile SingularAttribute<ReasonForLossEntity, String> nome;
    public static volatile ListAttribute<ReasonForLossEntity, NegotiationEntity> negociacoes;
}
