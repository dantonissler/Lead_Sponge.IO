package com.leadsponge.IO.models.contato;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.email.Email;
import com.leadsponge.IO.models.telefone.Telefone;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contato.class)
public abstract class Contato_ {
	public static volatile SingularAttribute<Contato, Long> id;
	public static volatile SingularAttribute<Contato, String> nome;
	public static volatile SingularAttribute<Contato, String> cargo;
	public static volatile ListAttribute<Contato, Telefone> telefoneContato;
	public static volatile ListAttribute<Contato, Email> emailContato;
	public static volatile SingularAttribute<Contato, Cliente> clienteContato;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String CARGO = "cargo";
	public static final String TELEFONE = "telefoneContato";
	public static final String EMAIL = "emailContato";
	public static final String CLIENTE = "clienteContato";
}
