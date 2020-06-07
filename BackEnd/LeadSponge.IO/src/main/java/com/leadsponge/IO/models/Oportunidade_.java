package com.leadsponge.IO.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Oportunidade.class)
public abstract class Oportunidade_ {
	public static volatile SingularAttribute<Oportunidade, Long> id;
	public static volatile SingularAttribute<Oportunidade, String> nome;
	public static volatile SingularAttribute<Oportunidade, Fonte> fonte;
	public static volatile SingularAttribute<Oportunidade, Campanha> campanha;
	public static volatile ListAttribute<Oportunidade, Produto> produtos;
	public static volatile SingularAttribute<Oportunidade, String> avaliacao;
	public static volatile SingularAttribute<Oportunidade, String> quantidadeProduto;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String FONTE = "fonte";
	public static final String CAMPANHA = "campanha";
	public static final String PRODUTOS = "produtos";
	public static final String AVALIACAO = "avaliacao";
	public static final String QUANTIDADEPRODUTO = "quantidadeProduto";
	
}
