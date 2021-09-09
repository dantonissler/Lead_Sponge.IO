package br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstagioNegociacaoEntity.class)
public abstract class EstagioNegociacaoEntity_ {
	public static volatile SingularAttribute<EstagioNegociacaoEntity, Long> id;
	public static volatile SingularAttribute<EstagioNegociacaoEntity, String> nome;
	public static volatile SingularAttribute<EstagioNegociacaoEntity, String> apelido;
	public static volatile SingularAttribute<EstagioNegociacaoEntity, Integer> posicao;
	public static volatile ListAttribute<EstagioNegociacaoEntity, NegociacaoEntity> negociacoes;
}
