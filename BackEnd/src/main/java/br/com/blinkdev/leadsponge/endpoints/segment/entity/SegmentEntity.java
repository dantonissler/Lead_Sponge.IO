package br.com.blinkdev.leadsponge.endpoints.segment.entity;

import br.com.blinkdev.leadsponge.endpoints.View;
import br.com.blinkdev.leadsponge.endpoints.customer.entity.CustomerEntity;
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
@Table(name = "segment")
@TableGenerator(name = "segment_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "segment", allocationSize = 1)
public class SegmentEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(View.Segmento.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "segment_id")
    private Long id;

    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    @ManyToMany(mappedBy = "segmentos", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("segmentos")
    @ToString.Exclude
    private List<CustomerEntity> clientes;

    public SegmentEntity(Long id) {
        this.id = id;
    }

    public SegmentEntity(String nome) {
        this.nome = nome;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SegmentEntity segmento = (SegmentEntity) o;

        return Objects.equals(id, segmento.id);
    }

    @Override
    public int hashCode() {
        return 576652681;
    }
}
