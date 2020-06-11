package com.leadsponge.IO.models.telefone;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.contato.Contato;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Telefone.class)
public abstract class Telefone_ {
	public static volatile SingularAttribute<Telefone, Long> id;
	public static volatile SingularAttribute<Telefone, String> numero;
	public static volatile SingularAttribute<Telefone, TipoTelefone> tipo;
	public static volatile SingularAttribute<Telefone, Contato> contatoTelefone;
	public static final String ID = "id";
	public static final String NUMERO = "numero";
	public static final String TIPO = "tipo";
	public static final String CONTATO = "contatoTelefone";
}
