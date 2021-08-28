package br.com.blinkdev.leadsponge.models.fonteNegociacao;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
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
@Table(name = "fonte_negociacao")
@TableGenerator(name = "fonte_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "fonte_negociacao", allocationSize = 1)
public class FonteNegociacao extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.FonteNegociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "fonte_negociacao_id")
    private Long id;

    @Size(min = 4, max = 50, message = "{nome.size}")
    @NotNull(message = "{nome.null}")
    @Column(name = "nome")
    private String nome;

    @JsonIgnoreProperties("fonte")
    @OneToMany(mappedBy = "fonte", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Negociacao> negociacoes;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public FonteNegociacao(@Size(min = 4, max = 50) String nome) {
        super();
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FonteNegociacao that = (FonteNegociacao) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 142639717;
    }
}
