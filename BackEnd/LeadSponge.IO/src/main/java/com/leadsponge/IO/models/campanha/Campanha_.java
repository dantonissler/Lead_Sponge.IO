package com.leadsponge.IO.models.campanha;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.negociacao.Negociacao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Campanha.class)
public abstract class Campanha_ {
	public static volatile SingularAttribute<Campanha, Long> id;
	public static volatile SingularAttribute<Campanha, String> nome;
	public static volatile ListAttribute<Campanha, Negociacao> negociacoes;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String NEGOCIACOES = "negociacoes";
}
