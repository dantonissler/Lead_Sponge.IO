package br.com.blinkdev.leadsponge.endPoints.user.entity;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;

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
    public static volatile SingularAttribute<UserEntity, String> nomeCompleto;
    public static volatile SingularAttribute<UserEntity, String> email;
    public static volatile SingularAttribute<UserEntity, Boolean> enabled;
    public static volatile SingularAttribute<UserEntity, String> foto;
    public static volatile SingularAttribute<UserEntity, String> urlFoto;
    public static volatile SetAttribute<UserEntity, RoleEntity> roles;
    public static volatile ListAttribute<UserEntity, TarefaEntity> tarefas;
    public static volatile SingularAttribute<UserEntity, String> password;
    public static volatile ListAttribute<UserEntity, ClienteEntity> clientesSeguidos;
}