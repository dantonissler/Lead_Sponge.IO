package br.com.blinkdev.leadsponge.endPoints.role.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "roles", itemRelation = "role")
@JsonInclude(Include.NON_NULL)
public class RoleModel extends RepresentationModel<RoleModel> {
    private Long id;
    private String nome;
}
