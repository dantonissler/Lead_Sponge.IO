package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NegotiationStyleEntity.class)
public abstract class NegotiationStyleEntity_ {
    public static volatile SingularAttribute<NegotiationStyleEntity, Long> id;
    public static volatile SingularAttribute<NegotiationStyleEntity, String> nome;
    public static volatile SingularAttribute<NegotiationStyleEntity, String> apelido;
    public static volatile SingularAttribute<NegotiationStyleEntity, Integer> posicao;
    public static volatile ListAttribute<NegotiationStyleEntity, NegotiationEntity> negociacoes;
}
