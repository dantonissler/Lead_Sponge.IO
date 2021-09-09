package br.com.blinkdev.leadsponge.endPoints.email.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmailEntity.class)
public abstract class EmailEntity_ {
	public static volatile SingularAttribute<EmailEntity, Long> id;
	public static volatile SingularAttribute<EmailEntity, String> email;
	public static volatile SingularAttribute<EmailEntity, ContatoEntity> contato;
}
