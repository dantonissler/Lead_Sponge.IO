package br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity;

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
public class HistoryNegotiationStylePK implements Serializable {

    @JoinColumn(name = "negotiation_id")
    private Long negotiation_id;

    @JoinColumn(name = "negotiation_style_id")
    private Long negotiation_style_id;
}
