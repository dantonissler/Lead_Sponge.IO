package br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.enumeration.TipoReincidencia;
import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.enumeration.TipoDesconto;
import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negociacao_produto")
@TableGenerator(name = "negociacao_produto_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "negociacao_produto", allocationSize = 1)
public class NegociacaoProdutoEntity extends UserDateAudit {

    public NegociacaoProdutoEntity(Long id, @NotNull Integer quantidade, @NotNull BigDecimal valor, @NotNull TipoReincidencia reincidencia, @NotNull ProdutoEntity produto, @NotNull NegociacaoEntity negociacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.reincidencia = reincidencia;
        this.produto = produto;
        this.negociacao = negociacao;
    }

    @Id
    @Column(name = "id")
    @JsonView(View.NegociacaoProduto.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "negociacao_produto_id")
    private Long id;

    @NotNull(message = "{quantidade.null}")
    @Column(name = "quantidade")
    private Integer quantidade;

    @NotNull(message = "{valor.null}")
    @Column(name = "valor")
    private BigDecimal valor;

    @NotNull(message = "{reincidencia.null}")
    @Column(name = "tipo_reincidencia")
    private TipoReincidencia reincidencia;

    @Column(name = "desconto")
    private BigDecimal desconto;

    @Column(name = "tem_desconto")
    private Boolean temDesconto;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "tipo_desconto")
    private TipoDesconto tipoDesconto;

    @ManyToOne
    @NotNull(message = "{produto.null}")
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "negociacao_id")
    private NegociacaoEntity negociacao;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NegociacaoProdutoEntity that = (NegociacaoProdutoEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 91508497;
    }
}
