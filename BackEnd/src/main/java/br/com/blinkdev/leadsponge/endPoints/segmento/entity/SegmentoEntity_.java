package br.com.blinkdev.leadsponge.endPoints.segmento.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SegmentoEntity.class)
public abstract class SegmentoEntity_ {
	public static volatile SingularAttribute<SegmentoEntity, Long> id;
	public static volatile SingularAttribute<SegmentoEntity, String> nome;
	public static volatile ListAttribute<SegmentoEntity, ClienteEntity> clientes;
}
