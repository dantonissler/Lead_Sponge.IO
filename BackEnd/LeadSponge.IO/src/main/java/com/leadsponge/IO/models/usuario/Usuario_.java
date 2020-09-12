package com.leadsponge.IO.models.usuario;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.models.tarefa.Tarefa;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, String> username;
	public static volatile SingularAttribute<Usuario, String> nomeCompleto;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, Boolean> enabled;
	public static volatile SingularAttribute<Usuario, String> foto;
	public static volatile SingularAttribute<Usuario, String> urlFoto;
	public static volatile SetAttribute<Usuario, Role> roles;
	public static volatile ListAttribute<Usuario, Tarefa> tarefas;
	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile ListAttribute<Usuario, Cliente> clientesSeguidos;
}