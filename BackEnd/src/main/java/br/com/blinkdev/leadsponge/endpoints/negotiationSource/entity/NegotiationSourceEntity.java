package br.com.blinkdev.leadsponge.endpoints.negotiationSource.entity;

import br.com.blinkdev.leadsponge.endpoints.View;
import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
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
@Table(name = "negotiation_source")
@TableGenerator(name = "negotiation_source_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "negotiation_source", allocationSize = 1)
public class NegotiationSourceEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.FonteNegociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "negotiation_source_id")
    private Long id;

    @Size(min = 4, max = 50, message = "{nome.size}")
    @NotNull(message = "{nome.null}")
    private String nome;

    @JsonIgnoreProperties("fonte")
    @OneToMany(mappedBy = "fonte", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<NegotiationEntity> negociacoes;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public NegotiationSourceEntity(@Size(min = 4, max = 50) String nome) {
        super();
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NegotiationSourceEntity that = (NegotiationSourceEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 142639717;
    }
}
