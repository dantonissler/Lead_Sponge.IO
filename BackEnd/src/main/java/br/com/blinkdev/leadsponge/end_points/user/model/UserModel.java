package br.com.blinkdev.leadsponge.end_points.user.model;

import br.com.blinkdev.leadsponge.end_points.customer.model.CustomerModel;
import br.com.blinkdev.leadsponge.end_points.role.model.RoleModel;
import br.com.blinkdev.leadsponge.end_points.task.model.TaskModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "users", itemRelation = "user")
@JsonInclude(Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> {
    private Long id;
    private String username;
    private String nomeCompleto;
    private String email;
    private Boolean enabled;

    private Set<RoleModel> roles;
    private List<TaskModel> tarefas;
    private List<CustomerModel> clientesSeguidos;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    private String createdByUser;
    private String modifiedByUser;
}
