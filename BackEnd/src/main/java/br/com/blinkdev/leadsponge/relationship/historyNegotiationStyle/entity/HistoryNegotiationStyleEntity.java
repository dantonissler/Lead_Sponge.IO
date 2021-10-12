package br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "history_negotiation_style")
public class HistoryNegotiationStyleEntity extends UserDateAudit {

    @ManyToOne
    @MapsId("negotiation_id")
    @JoinColumn(name = "negotiation_id")
    NegotiationEntity negotiation;
    @ManyToOne
    @MapsId("negotiation_style_id")
    @JoinColumn(name = "negotiation_style_id")
    NegotiationStyleEntity negotiationStyle;
    @EmbeddedId
    private HistoryNegotiationStylePK id;
    @NotNull
    private String apelido;

    @NotNull
    @Column(name = "data_mudanca")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate dataMudanca;
}
