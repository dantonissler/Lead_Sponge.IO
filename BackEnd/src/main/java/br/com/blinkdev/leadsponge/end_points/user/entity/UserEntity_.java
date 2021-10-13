package br.com.blinkdev.leadsponge.end_points.user.entity;

import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.end_points.task.entity.TaskEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {
    public static volatile SingularAttribute<UserEntity, Long> id;
    public static volatile SingularAttribute<UserEntity, String> username;
    public static volatile SingularAttribute<UserEntity, String> fullName;
    public static volatile SingularAttribute<UserEntity, String> email;
    public static volatile SingularAttribute<UserEntity, Boolean> enabled;
    public static volatile SingularAttribute<UserEntity, String> photo;
    public static volatile SingularAttribute<UserEntity, String> urlPhoto;
    public static volatile SetAttribute<UserEntity, RoleEntity> roles;
    public static volatile ListAttribute<UserEntity, TaskEntity> tarefas;
    public static volatile SingularAttribute<UserEntity, String> password;
    public static volatile ListAttribute<UserEntity, CustomerEntity> clientesSeguidos;
}