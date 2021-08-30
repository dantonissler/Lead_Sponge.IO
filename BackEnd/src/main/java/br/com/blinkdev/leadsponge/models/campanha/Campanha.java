package br.com.blinkdev.leadsponge.models.campanha;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campanhas")
@TableGenerator(name = "campanha_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "campanhas", allocationSize = 1)
public class Campanha extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(View.Campanha.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "campanha_id")
    private Long id;

    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    private String descricao;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Campanha campanha = (Campanha) o;

        return Objects.equals(id, campanha.id);
    }

    @Override
    public int hashCode() {
        return 561786513;
    }
}
