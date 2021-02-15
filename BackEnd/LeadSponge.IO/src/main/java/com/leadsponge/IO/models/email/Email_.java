package com.leadsponge.IO.models.email;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.leadsponge.IO.models.contato.Contato;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Email.class)
public abstract class Email_ {
	public static volatile SingularAttribute<Email, Long> id;
	public static volatile SingularAttribute<Email, String> email;
	public static volatile SingularAttribute<Email, Contato> contato;
}
