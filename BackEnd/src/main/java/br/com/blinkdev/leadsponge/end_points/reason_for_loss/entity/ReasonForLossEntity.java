package br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reason_for_loss")
@TableGenerator(name = "reason_for_loss_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "reason_for_loss", allocationSize = 1)
public class ReasonForLossEntity extends UserDateAudit {

    @Id
    @JsonView(View.MotivoPerda.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "reason_for_loss_id")
    private Long id;

    @Size(min = 4, max = 50, message = "{nome.size}")
    @NotNull(message = "{nome.null}")
    private String name;

    @JsonIgnoreProperties("motivoPerda")
    @OneToMany(mappedBy = "reasonForLoss", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<NegotiationEntity> negotiations;
}
