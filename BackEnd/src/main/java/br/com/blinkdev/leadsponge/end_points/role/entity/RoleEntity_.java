package br.com.blinkdev.leadsponge.end_points.role.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleEntity.class)
public abstract class RoleEntity_ {
	public static volatile SingularAttribute<RoleEntity, Long> id;
	public static volatile SingularAttribute<RoleEntity, String> name;
	public static volatile SetAttribute<RoleEntity, UserEntity> users;
}
