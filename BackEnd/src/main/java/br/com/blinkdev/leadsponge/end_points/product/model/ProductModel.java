package br.com.blinkdev.leadsponge.end_points.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "negotiatio_source", itemRelation = "negotiatios_sources")
@JsonInclude(Include.NON_NULL)
public class ProductModel extends RepresentationModel<ProductModel> {
    private Long id;
    private String name;
    private String description;
    private BigDecimal value;
    private Boolean visibility;
}
