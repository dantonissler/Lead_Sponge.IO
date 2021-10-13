package br.com.blinkdev.leadsponge.end_points.task.model;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.task.enumeration.KindTask;
import br.com.blinkdev.leadsponge.end_points.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "tasks", itemRelation = "task")
@JsonInclude(Include.NON_NULL)
public class TaskModel extends RepresentationModel<TaskModel> {
    private Long id;
    private String subject;
    private LocalDateTime appointment;
    private Boolean done;
    private LocalDateTime timePerformed;
    private KindTask kind;

    private UserModel usuario;
    private NegotiationEntity negotiation;
}
