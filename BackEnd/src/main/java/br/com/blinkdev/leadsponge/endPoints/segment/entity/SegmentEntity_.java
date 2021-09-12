package br.com.blinkdev.leadsponge.endPoints.segment.entity;

import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SegmentEntity.class)
public abstract class SegmentEntity_ {
    public static volatile SingularAttribute<SegmentEntity, Long> id;
    public static volatile SingularAttribute<SegmentEntity, String> nome;
    public static volatile ListAttribute<SegmentEntity, CustomerEntity> clientes;
}
