package br.com.blinkdev.leadsponge.end_points.negotiation_source.entity;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegotiationSourceEntity.class)
public abstract class NegotiationSourceEntity_ {
    public static volatile SingularAttribute<NegotiationSourceEntity, Long> id;
    public static volatile SingularAttribute<NegotiationSourceEntity, String> name;
    public static volatile ListAttribute<NegotiationSourceEntity, NegotiationEntity> Negotiations;
}
