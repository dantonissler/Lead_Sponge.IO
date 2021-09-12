package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity;

import br.com.blinkdev.leadsponge.endPoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiation.enumeration.RecidivismType;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.enumeration.DiscountType;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

// TODO: Estudar a possibilidade dessa tabela entrar como relacionamento e ter um PK cruzando o product e negotiation
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negotiation_product")
@TableGenerator(name = "negociacao_produto_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "negotiation_product", allocationSize = 1)
public class NegotiationProductEntity extends UserDateAudit {

    @Id
    @JsonView(View.NegociacaoProduto.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "negociacao_produto_id")
    private Long id;

    @NotNull(message = "{quantidade.null}")
    private Integer quantidade;

    @NotNull(message = "{valor.null}")
    private BigDecimal valor;
    @Column(name = "tipo_desconto")
    private DiscountType tipoDesconto;

    private BigDecimal desconto;

    private Boolean temDesconto;

    private BigDecimal total;

    @NotNull(message = "{reincidencia.null}")
    @Column(name = "tipo_reincidencia")
    private RecidivismType reincidencia;

    @ManyToOne
    @NotNull(message = "{produto.null}")
    @JoinColumn(name = "produto_id")
    private ProductEntity produto;

    @ManyToOne
    @JoinColumn(name = "negociacao_id")
    private NegotiationEntity negociacao;

    public NegotiationProductEntity(Long id, @NotNull Integer quantidade, @NotNull BigDecimal valor, @NotNull RecidivismType reincidencia, @NotNull ProductEntity produto, @NotNull NegotiationEntity negociacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.reincidencia = reincidencia;
        this.produto = produto;
        this.negociacao = negociacao;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NegotiationProductEntity that = (NegotiationProductEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 91508497;
    }
}
