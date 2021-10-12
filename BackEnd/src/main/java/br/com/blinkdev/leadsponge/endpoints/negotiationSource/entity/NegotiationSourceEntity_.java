package br.com.blinkdev.leadsponge.endpoints.negotiationSource.entity;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegotiationSourceEntity.class)
public abstract class NegotiationSourceEntity_ {
    public static volatile SingularAttribute<NegotiationSourceEntity, Long> id;
    public static volatile SingularAttribute<NegotiationSourceEntity, String> nome;
    public static volatile ListAttribute<NegotiationSourceEntity, NegotiationEntity> negociacoes;
}
