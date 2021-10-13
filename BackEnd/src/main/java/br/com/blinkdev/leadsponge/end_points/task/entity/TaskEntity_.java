package br.com.blinkdev.leadsponge.end_points.task.entity;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.task.enumeration.KindTask;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TaskEntity.class)
public abstract class TaskEntity_ {
    public static volatile SingularAttribute<TaskEntity, Long> id;
    public static volatile SingularAttribute<TaskEntity, String> subject;
    public static volatile SingularAttribute<TaskEntity, String> description;
    public static volatile SingularAttribute<TaskEntity, LocalDateTime> appointment;
    public static volatile SingularAttribute<TaskEntity, KindTask> kind;
    public static volatile SingularAttribute<TaskEntity, UserEntity> user;
    public static volatile SingularAttribute<TaskEntity, Boolean> done;
    public static volatile SingularAttribute<TaskEntity, LocalDateTime> timePerformed;
    public static volatile SingularAttribute<TaskEntity, NegotiationEntity> negotiation;
}
