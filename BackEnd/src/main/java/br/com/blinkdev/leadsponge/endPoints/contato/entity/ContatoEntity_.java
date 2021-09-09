package br.com.blinkdev.leadsponge.endPoints.contato.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContatoEntity.class)
public abstract class ContatoEntity_ {
	public static volatile SingularAttribute<ContatoEntity, Long> id;
	public static volatile SingularAttribute<ContatoEntity, String> nome;
	public static volatile SingularAttribute<ContatoEntity, String> cargo;
	public static volatile ListAttribute<ContatoEntity, Telefone> telefone;
	public static volatile ListAttribute<ContatoEntity, EmailEntity> email;
	public static volatile SingularAttribute<ContatoEntity, ClienteEntity> cliente;
}
