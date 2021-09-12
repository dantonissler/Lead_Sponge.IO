package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.model;

import br.com.blinkdev.leadsponge.endPoints.Product.model.ProductModel;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.RecidivismType;
import br.com.blinkdev.leadsponge.endPoints.negotiation.model.NegotiationModel;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.enumeration.DiscountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "negotiatio_product", itemRelation = "negotiatios_products")
@JsonInclude(Include.NON_NULL)
public class NegotiationProductModel extends RepresentationModel<NegotiationProductModel> {

    private RecidivismType reincidencia;
    private Long id;
    private Integer quantidade;
    private BigDecimal valor;
    private DiscountType tipoDesconto;
    private BigDecimal desconto;
    private Boolean temDesconto;
    private BigDecimal total;

    private ProductModel produto;
    private NegotiationModel negociacao;
}
