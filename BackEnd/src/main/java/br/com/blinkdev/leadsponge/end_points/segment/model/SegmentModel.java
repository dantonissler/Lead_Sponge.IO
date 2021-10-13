package br.com.blinkdev.leadsponge.end_points.segment.model;

import br.com.blinkdev.leadsponge.end_points.customer.model.CustomerModel;
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
@Relation(collectionRelation = "segments", itemRelation = "segment")
@JsonInclude(Include.NON_NULL)
public class SegmentModel extends RepresentationModel<SegmentModel> {
    private Long id;
    private String name;

    private List<CustomerModel> customers;
}
