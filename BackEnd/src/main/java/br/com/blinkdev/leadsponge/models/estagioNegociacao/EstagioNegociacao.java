package br.com.blinkdev.leadsponge.models.estagioNegociacao;

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
@Table(name = "estagio_negociacao")
@TableGenerator(name = "estagio_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "estagio_negociacao", allocationSize = 1)
public class EstagioNegociacao extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    public EstagioNegociacao(@NotNull @Size(min = 4, max = 50) String nome, @NotNull @Size(max = 10) String apelido, @NotNull Integer posicao) {
        this.nome = nome;
        this.apelido = apelido;
        this.posicao = posicao;
    }

    @Id
    @Column(name = "id")
    @JsonView(View.EstagioNegociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "estagio_negociacao_id")
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

    @JsonIgnoreProperties("estagio")
    @OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Negociacao> negociacoes;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EstagioNegociacao that = (EstagioNegociacao) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 712480629;
    }
}
