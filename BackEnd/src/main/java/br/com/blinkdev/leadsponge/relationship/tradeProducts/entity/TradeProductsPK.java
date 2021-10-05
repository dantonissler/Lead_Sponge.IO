package br.com.blinkdev.leadsponge.relationship.tradeProducts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeProductsPK implements Serializable {

    @JoinColumn(name = "negotiation_id")
    private Long negotiation_id;

    @JoinColumn(name = "product_id")
    private Long product_id;
}
