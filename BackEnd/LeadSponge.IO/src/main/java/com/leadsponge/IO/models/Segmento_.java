package com.leadsponge.IO.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Segmento.class)
public abstract class Segmento_ {
	public static volatile SingularAttribute<Campanha, Long> id;
	public static volatile SingularAttribute<Campanha, String> nome;
	public static volatile ListAttribute<Campanha, Cliente> clientes;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String CLIENTES = "clientes";
}
