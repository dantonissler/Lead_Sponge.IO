package br.com.blinkdev.leadsponge.models.contato;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.email.Email;
import br.com.blinkdev.leadsponge.models.telefone.Telefone;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contato.class)
public abstract class Contato_ {
	public static volatile SingularAttribute<Contato, Long> id;
	public static volatile SingularAttribute<Contato, String> nome;
	public static volatile SingularAttribute<Contato, String> cargo;
	public static volatile ListAttribute<Contato, Telefone> telefone;
	public static volatile ListAttribute<Contato, Email> email;
	public static volatile SingularAttribute<Contato, Cliente> cliente;
}
