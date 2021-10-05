package br.com.blinkdev.leadsponge.endPoints.Product.model;

import br.com.blinkdev.leadsponge.relationship.tradeProducts.model.TradeProductsModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
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
public class ProductModel extends RepresentationModel<ProductModel> {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Boolean visibilidade;

    private List<TradeProductsModel> negociacaoProdutos;
}
