package com.leadsponge.IO.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, String> nome;
	public static volatile SetAttribute<Role, Usuario> usuarios;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String USUARIOS = "usuarios";
	
}
