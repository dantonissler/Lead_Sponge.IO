package br.com.blinkdev.leadsponge.endpoints.Product.entity;

import br.com.blinkdev.leadsponge.endpoints.View;
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
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@TableGenerator(name = "produto_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "product", allocationSize = 1)
public class ProductEntity extends UserDateAudit {

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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductEntity produto = (ProductEntity) o;

        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return 1852208554;
    }
}
