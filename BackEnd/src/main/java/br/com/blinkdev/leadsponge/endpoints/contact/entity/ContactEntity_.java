package br.com.blinkdev.leadsponge.endpoints.contact.entity;

import br.com.blinkdev.leadsponge.resources.address.entity.AddressEntity;
import br.com.blinkdev.leadsponge.endpoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.resources.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.resources.phone.entity.PhoneEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactEntity.class)
public abstract class ContactEntity_ {
    public static volatile SingularAttribute<ContactEntity, Long> id;
    public static volatile SingularAttribute<ContactEntity, String> nome;
    public static volatile SingularAttribute<ContactEntity, String> cargo;
    public static volatile ListAttribute<ContactEntity, PhoneEntity> phone;
    public static volatile ListAttribute<ContactEntity, EmailEntity> email;
    public static volatile ListAttribute<ContactEntity, AddressEntity> address;
    public static volatile SingularAttribute<ContactEntity, CustomerEntity> customer;
}
