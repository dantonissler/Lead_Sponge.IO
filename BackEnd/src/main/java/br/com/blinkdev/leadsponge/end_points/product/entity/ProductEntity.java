package br.com.blinkdev.leadsponge.end_points.product.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@TableGenerator(name = "produto_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "product", allocationSize = 1)
public class ProductEntity extends UserDateAudit {

    @Id
    @JsonView(View.Produto.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "produto_id")
    private Long id;

    @Size(min = 4, max = 100, message = "{produto.nome.size}")
    @NotNull(message = "{nome.null}")
    private String name;

    @Size(max = 150, message = "{produto.descricao.size}")
    private String description;

    @NotNull(message = "{valor.null}")
    private BigDecimal value;

    @NotNull(message = "{visibilidade.null}")
    private Boolean visibility;
}
