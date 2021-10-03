package br.com.blinkdev.leadsponge.endPoints.customer.entity;

import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerEntity.class)
public abstract class CustomerEntity_ {
    public static volatile SingularAttribute<CustomerEntity, Long> id;
    public static volatile SingularAttribute<CustomerEntity, String> nome;
    public static volatile SingularAttribute<CustomerEntity, String> url;
    public static volatile SingularAttribute<CustomerEntity, String> resumo;
    public static volatile ListAttribute<CustomerEntity, NegotiationEntity> negociacoes;
    public static volatile ListAttribute<CustomerEntity, ContactEntity> contact;
    public static volatile ListAttribute<CustomerEntity, SegmentEntity> segmentos;
    public static volatile SingularAttribute<CustomerEntity, UserEntity> responsavel;
    public static volatile ListAttribute<CustomerEntity, UserEntity> seguidores;
}