package br.com.blinkdev.leadsponge.end_points.customer.model;

import br.com.blinkdev.leadsponge.end_points.contact.model.ContactModel;
import br.com.blinkdev.leadsponge.end_points.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.end_points.segment.model.SegmentModel;
import br.com.blinkdev.leadsponge.end_points.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "customers", itemRelation = "customers")
@JsonInclude(Include.NON_NULL)
public class CustomerModel extends RepresentationModel<UserModel> {

    private Long id;
    private String name;
    private String url;
    private String summary;

    private List<ContactModel> contato;

    private List<NegotiationModel> negotiations;

    private List<SegmentModel> segmentos;

    private List<UserModel> seguidores;

    private UserModel responsavel;
}
