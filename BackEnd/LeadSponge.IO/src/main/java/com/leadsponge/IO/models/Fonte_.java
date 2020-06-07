package com.leadsponge.IO.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Fonte.class)
public abstract class Fonte_ {
	public static volatile SingularAttribute<Fonte, Long> id;
	public static volatile SingularAttribute<Fonte, String> nome;
	public static volatile ListAttribute<Fonte, Oportunidade> oportunidade;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String OPORTUNIDADE = "oportunidade";
}