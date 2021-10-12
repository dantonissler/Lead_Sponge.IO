package br.com.blinkdev.leadsponge.endpoints.negotiationSource.model;

import br.com.blinkdev.leadsponge.endpoints.negotiation.model.NegotiationModel;
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
@Relation(collectionRelation = "negotiatio_source", itemRelation = "negotiatios_sources")
@JsonInclude(Include.NON_NULL)
public class NegotiationSourceModel extends RepresentationModel<NegotiationSourceModel> {
    private Long id;
    private String nome;

    private List<NegotiationModel> negociacoes;
}
