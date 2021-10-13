package br.com.blinkdev.leadsponge.end_points.negotiation_source.model;

import br.com.blinkdev.leadsponge.end_points.negotiation.model.NegotiationModel;
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
    private String name;

    private List<NegotiationModel> negotiations;
}
