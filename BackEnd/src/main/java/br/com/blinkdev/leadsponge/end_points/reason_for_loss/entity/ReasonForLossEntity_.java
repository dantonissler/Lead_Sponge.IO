package br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReasonForLossEntity.class)
public abstract class ReasonForLossEntity_ {
    public static volatile SingularAttribute<ReasonForLossEntity, Long> id;
    public static volatile SingularAttribute<ReasonForLossEntity, String> name;
    public static volatile ListAttribute<ReasonForLossEntity, NegotiationEntity> negotiations;
}
