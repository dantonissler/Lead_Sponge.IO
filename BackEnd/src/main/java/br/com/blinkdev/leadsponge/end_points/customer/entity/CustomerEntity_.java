package br.com.blinkdev.leadsponge.end_points.customer.entity;

import br.com.blinkdev.leadsponge.end_points.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerEntity.class)
public abstract class CustomerEntity_ {
    public static volatile SingularAttribute<CustomerEntity, Long> id;
    public static volatile SingularAttribute<CustomerEntity, String> name;
    public static volatile SingularAttribute<CustomerEntity, String> url;
    public static volatile SingularAttribute<CustomerEntity, String> summary;
    public static volatile ListAttribute<CustomerEntity, NegotiationEntity> negotiations;
    public static volatile ListAttribute<CustomerEntity, ContactEntity> contact;
    public static volatile ListAttribute<CustomerEntity, SegmentEntity> segmentos;
    public static volatile SingularAttribute<CustomerEntity, UserEntity> responsavel;
    public static volatile ListAttribute<CustomerEntity, UserEntity> seguidores;
}