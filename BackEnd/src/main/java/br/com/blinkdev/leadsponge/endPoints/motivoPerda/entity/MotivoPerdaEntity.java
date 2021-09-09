package br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
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
@Table(name = "motivo_perda")
@TableGenerator(name = "motivo_perda_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "motivo_perda", allocationSize = 1)
public class MotivoPerdaEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.MotivoPerda.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "motivo_perda_id")
    private Long id;

    @Size(min = 4, max = 50, message = "{nome.size}")
    @NotNull(message = "{nome.null}")
    @Column(name = "nome")
    private String nome;

    @JsonIgnoreProperties("motivoPerda")
    @OneToMany(mappedBy = "motivoPerda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<NegociacaoEntity> negociacoes;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MotivoPerdaEntity that = (MotivoPerdaEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 136063472;
    }
}
