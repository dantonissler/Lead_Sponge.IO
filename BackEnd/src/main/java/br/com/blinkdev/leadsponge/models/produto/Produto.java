package br.com.blinkdev.leadsponge.models.produto;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
@TableGenerator(name = "produto_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "produtos", allocationSize = 1)
public class Produto extends UserDateAudit {

    public Produto(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @JsonView(View.Produto.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "produto_id")
    private Long id;

    @Column(name = "nome")
    @Size(min = 4, max = 100, message = "{produto.nome.size}")
    @NotNull(message = "{nome.null}")
    private String nome;

    @Size(max = 150, message = "{produto.descricao.size}")
    private String descricao;

    @NotNull(message = "{valor.null}")
    private BigDecimal valor;

    @NotNull(message = "{visibilidade.null}")
    private Boolean visibilidade;

    @JsonIgnore
    @JsonIgnoreProperties(value = {"negociacao", "produto"})
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<NegociacaoProduto> negociacaoProdutos;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Produto produto = (Produto) o;

        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return 1852208554;
    }
}
