package br.com.blinkdev.leadsponge.relationship.tradeProducts.entity;

import br.com.blinkdev.leadsponge.end_points.product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.enumeration.KindRecidivism;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.enumeration.KindDiscount;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "trade_product")
public class TradeProductsEntity extends UserDateAudit {

    @ManyToOne
    @MapsId("negotiation_id")
    @JoinColumn(name = "negotiation_id")
    NegotiationEntity negotiation;
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    ProductEntity product;
    @EmbeddedId
    private TradeProductsPK id;
    @NotNull(message = "{quantidade.null}")
    private Integer quantidade;

    @NotNull(message = "{valor.null}")
    private BigDecimal valor;

    @Column(name = "tipo_desconto")
    private KindDiscount tipoDesconto;

    private BigDecimal desconto;

    private Boolean temDesconto;

    private BigDecimal total;

    @NotNull(message = "{reincidencia.null}")
    @Column(name = "tipo_reincidencia")
    private KindRecidivism reincidencia;
}
