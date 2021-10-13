package br.com.blinkdev.leadsponge.end_points.campaign.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "camaign")
@TableGenerator(name = "camaign_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "camaign", allocationSize = 1)
public class CampaignEntity extends UserDateAudit {

    @Id
    @JsonView(View.Campaign.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "camaign_id")
    private Long id;

    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String name;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CampaignEntity that = (CampaignEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
