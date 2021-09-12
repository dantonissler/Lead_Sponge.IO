package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reason_for_loss")
@TableGenerator(name = "reason_for_loss_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "reason_for_loss", allocationSize = 1)
public class ReasonForLossEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.MotivoPerda.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "reason_for_loss_id")
    private Long id;

    @Size(min = 4, max = 50, message = "{nome.size}")
    @NotNull(message = "{nome.null}")
    private String nome;

    @JsonIgnoreProperties("motivoPerda")
    @OneToMany(mappedBy = "motivoPerda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<NegotiationEntity> negociacoes;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReasonForLossEntity that = (ReasonForLossEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 136063472;
    }
}
