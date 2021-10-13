package br.com.blinkdev.leadsponge.end_points.segment.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
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
@Table(name = "segment")
@TableGenerator(name = "segment_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "segment", allocationSize = 1)
public class SegmentEntity extends UserDateAudit {

    @Id
    @JsonView(View.Segmento.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "segment_id")
    private Long id;

    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String name;

    @ManyToMany(mappedBy = "segmentos", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("segmentos")
    @ToString.Exclude
    private List<CustomerEntity> customers;

    public SegmentEntity(String nome) {
        this.name = nome;
    }
}
