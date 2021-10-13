package br.com.blinkdev.leadsponge.end_points.negotiation_style.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegotiationStyleEntity.class)
public abstract class NegotiationStyleEntity_ {
    public static volatile SingularAttribute<NegotiationStyleEntity, Long> id;
    public static volatile SingularAttribute<NegotiationStyleEntity, String> name;
    public static volatile SingularAttribute<NegotiationStyleEntity, String> surname;
    public static volatile SingularAttribute<NegotiationStyleEntity, Integer> position;
}
