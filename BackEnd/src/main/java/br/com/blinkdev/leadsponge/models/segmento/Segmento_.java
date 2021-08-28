package br.com.blinkdev.leadsponge.models.segmento;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Segmento.class)
public abstract class Segmento_ {
	public static volatile SingularAttribute<Segmento, Long> id;
	public static volatile SingularAttribute<Segmento, String> nome;
	public static volatile ListAttribute<Segmento, Cliente> clientes;
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String CLIENTES = "clientes";
}
