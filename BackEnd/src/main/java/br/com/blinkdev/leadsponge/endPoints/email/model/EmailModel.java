package br.com.blinkdev.leadsponge.endPoints.email.model;

import br.com.blinkdev.leadsponge.endPoints.contact.model.ContactModel;
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
@Relation(collectionRelation = "emails", itemRelation = "email")
@JsonInclude(Include.NON_NULL)
public class EmailModel extends RepresentationModel<EmailModel> {
    private Long id;
    private String email;

    private ContactModel contato;
}
