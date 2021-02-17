package com.leadsponge.IO.models.negociacaoProduto;

import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.negociacao.TipoReincidencia;
import com.leadsponge.IO.models.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negociacao_produto")
@TableGenerator(name = "negociacao_produto_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "negociacao_produto", allocationSize = 1)
public class NegociacaoProduto extends UserDateAudit {

    public NegociacaoProduto(Long id, @NotNull Integer quantidade, @NotNull BigDecimal valor, @NotNull TipoReincidencia reincidencia, @NotNull Produto produto, @NotNull Negociacao negociacao) {
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
    private Produto produto;

    @ManyToOne
    @NotNull(message = "{negociacao.null}")
    @JoinColumn(name = "negociacao_id")
    private Negociacao negociacao;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NegociacaoProduto other = (NegociacaoProduto) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
