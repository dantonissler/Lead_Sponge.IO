package br.com.blinkdev.leadsponge.models.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
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
    private List<NegociacaoProduto> negociacaoProdutos;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
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
