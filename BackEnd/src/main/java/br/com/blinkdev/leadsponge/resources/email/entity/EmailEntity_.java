package br.com.blinkdev.leadsponge.resources.email.entity;

import br.com.blinkdev.leadsponge.end_points.contact.entity.ContactEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmailEntity.class)
public abstract class EmailEntity_ {
    public static volatile SingularAttribute<EmailEntity, Long> id;
    public static volatile SingularAttribute<EmailEntity, String> email;
    public static volatile SingularAttribute<EmailEntity, ContactEntity> contato;
}
