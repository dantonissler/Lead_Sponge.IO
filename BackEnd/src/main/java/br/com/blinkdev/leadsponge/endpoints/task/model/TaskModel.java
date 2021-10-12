package br.com.blinkdev.leadsponge.endpoints.task.model;

import br.com.blinkdev.leadsponge.endpoints.task.enumeration.TypeTask;
import br.com.blinkdev.leadsponge.endpoints.user.model.UserModel;
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
    private String assunto;
    private LocalDateTime horaMarcada;
    private TypeTask tipo;
    private UserModel usuario;
}
