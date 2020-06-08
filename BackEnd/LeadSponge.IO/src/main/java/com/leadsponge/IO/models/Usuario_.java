package com.leadsponge.IO.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, String> username;
	public static volatile SingularAttribute<Usuario, String> nomeCompleto;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, Boolean> enabled;
	public static volatile SetAttribute<Usuario, Role> roles;
	public static volatile ListAttribute<Usuario, Tarefa> tarefas;
	public static volatile SingularAttribute<Usuario, String> password;
	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String NOME_COMPLETO = "nomeCompleto";
	public static final String EMAIL = "email";
	public static final String ENABLED = "enabled";
	public static final String ROLES = "roles";
	public static final String TAREFAS = "tarefas";
	public static final String PASSWORD = "password";
}