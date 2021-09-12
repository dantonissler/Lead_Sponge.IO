package br.com.blinkdev.leadsponge.endPoints.task.entity;

import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.task.enumeration.TypeTask;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TaskEntity.class)
public abstract class TaskEntity_ {
    public static volatile SingularAttribute<TaskEntity, Long> id;
    public static volatile SingularAttribute<TaskEntity, String> assunto;
    public static volatile SingularAttribute<TaskEntity, String> descricao;
    public static volatile SingularAttribute<TaskEntity, LocalDateTime> horaMarcada;
    public static volatile SingularAttribute<TaskEntity, TypeTask> tipo;
    public static volatile SingularAttribute<TaskEntity, UserEntity> usuario;
    public static volatile SingularAttribute<TaskEntity, Boolean> realizda;
    public static volatile SingularAttribute<TaskEntity, LocalDateTime> horaRealizada;
    public static volatile SingularAttribute<TaskEntity, NegotiationEntity> negociacao;
}
