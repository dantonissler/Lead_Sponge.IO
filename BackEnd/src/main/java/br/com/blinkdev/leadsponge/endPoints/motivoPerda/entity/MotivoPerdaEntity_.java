package br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MotivoPerdaEntity.class)
public abstract class MotivoPerdaEntity_ {
	public static volatile SingularAttribute<MotivoPerdaEntity, Long> id;
	public static volatile SingularAttribute<MotivoPerdaEntity, String> nome;
	public static volatile ListAttribute<MotivoPerdaEntity, NegociacaoEntity> negociacoes;
}
