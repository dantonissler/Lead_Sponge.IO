package br.com.blinkdev.leadsponge.endPoints.phone.model;

import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.model.NegotiationStyleModel;
import br.com.blinkdev.leadsponge.endPoints.phone.enumeration.TypePhone;
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
@Relation(collectionRelation = "phones", itemRelation = "phone")
@JsonInclude(Include.NON_NULL)
public class PhoneModel extends RepresentationModel<NegotiationStyleModel> {
    private Long id;
    private String numero;
    private TypePhone tipo;

    private ContactEntity contato;
}
