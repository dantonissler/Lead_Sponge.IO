package br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FonteNegociacaoEntity.class)
public abstract class FonteNegociacaoEntity_ {
	public static volatile SingularAttribute<FonteNegociacaoEntity, Long> id;
	public static volatile SingularAttribute<FonteNegociacaoEntity, String> nome;
	public static volatile ListAttribute<FonteNegociacaoEntity, NegociacaoEntity> negociacoes;
}
