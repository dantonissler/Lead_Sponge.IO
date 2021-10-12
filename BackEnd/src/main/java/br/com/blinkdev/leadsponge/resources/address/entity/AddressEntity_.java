package br.com.blinkdev.leadsponge.resources.address.entity;

import br.com.blinkdev.leadsponge.endpoints.contact.entity.ContactEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddressEntity.class)
public abstract class AddressEntity_ {
    public static volatile SingularAttribute<AddressEntity, Long> id;
    public static volatile SingularAttribute<AddressEntity, String> zipCode;
    public static volatile SingularAttribute<AddressEntity, String> publicPlace;
    public static volatile SingularAttribute<AddressEntity, String> district;
    public static volatile SingularAttribute<AddressEntity, String> location;
    public static volatile SingularAttribute<AddressEntity, String> uf;
    public static volatile SingularAttribute<AddressEntity, ContactEntity> contact;
}
