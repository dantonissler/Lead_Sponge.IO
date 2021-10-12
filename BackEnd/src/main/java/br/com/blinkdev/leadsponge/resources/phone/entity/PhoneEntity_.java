package br.com.blinkdev.leadsponge.resources.phone.entity;

import br.com.blinkdev.leadsponge.endpoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.resources.phone.enumeration.TypePhone;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhoneEntity.class)
public abstract class PhoneEntity_ {
    public static volatile SingularAttribute<PhoneEntity, Long> id;
    public static volatile SingularAttribute<PhoneEntity, String> numero;
    public static volatile SingularAttribute<PhoneEntity, TypePhone> type;
    public static volatile SingularAttribute<PhoneEntity, Boolean> temWhatsapp;
    public static volatile SingularAttribute<PhoneEntity, String> codigoArea;
    public static volatile SingularAttribute<PhoneEntity, String> codigoPais;
    public static volatile SingularAttribute<PhoneEntity, ContactEntity> contato;
}
