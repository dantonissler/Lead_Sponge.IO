package br.com.blinkdev.leadsponge.end_points.negotiation_style.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "negotiation_style")
@TableGenerator(name = "negotiation_style_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "negotiation_style", allocationSize = 1)
public class NegotiationStyleEntity extends UserDateAudit {

    @Id
    @JsonView(View.EstagioNegociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "negotiation_style_id")
    private Long id;

    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String name;

    @NotNull(message = "{apelido.null}")
    @Size(max = 10, message = "{apelido.size}")
    private String surname;

    @NotNull(message = "{posicao.null}")
    private Integer position;

    public NegotiationStyleEntity(String name, String surname, Integer position) {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }
}
