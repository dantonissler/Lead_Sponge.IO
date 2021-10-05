package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negotiation_style")
@TableGenerator(name = "negotiation_style_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "negotiation_style", allocationSize = 1)
public class NegotiationStyleEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.EstagioNegociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "negotiation_style_id")
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    @Column(name = "apelido")
    @NotNull(message = "{apelido.null}")
    @Size(max = 10, message = "{apelido.size}")
    private String apelido;

    @Column(name = "posicao")
    @NotNull(message = "{posicao.null}")
    private Integer posicao;

    public NegotiationStyleEntity(@NotNull @Size(min = 4, max = 50) String nome, @NotNull @Size(max = 10) String apelido, @NotNull Integer posicao) {
        this.nome = nome;
        this.apelido = apelido;
        this.posicao = posicao;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NegotiationStyleEntity that = (NegotiationStyleEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 712480629;
    }
}
