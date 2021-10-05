package br.com.blinkdev.leadsponge.endPoints.customer.model;

import br.com.blinkdev.leadsponge.endPoints.contact.model.ContactModel;
import br.com.blinkdev.leadsponge.endPoints.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.endPoints.segment.model.SegmentModel;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
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
    private String nome;
    private String url;
    private String resumo;

    private List<ContactModel> contato;

    private List<NegotiationModel> negociacoes;

    private List<SegmentModel> segmentos;

    private List<UserModel> seguidores;

    private UserModel responsavel;
}
