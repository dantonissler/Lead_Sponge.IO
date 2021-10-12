package br.com.blinkdev.leadsponge.endpoints.negotiationStyle.model;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
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
@Relation(collectionRelation = "negotiatio_style", itemRelation = "negotiatios_styles")
@JsonInclude(Include.NON_NULL)
public class NegotiationStyleModel extends RepresentationModel<NegotiationStyleModel> {
    private Long id;
    private String nome;
    private String apelido;
    private Integer posicao;

    private List<NegotiationEntity> negociacoes;
}
