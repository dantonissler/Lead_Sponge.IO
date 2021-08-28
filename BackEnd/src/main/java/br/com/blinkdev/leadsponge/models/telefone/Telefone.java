package br.com.blinkdev.leadsponge.models.telefone;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.contato.Contato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "telefones")
@TableGenerator(name = "telefone_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "telefones", allocationSize = 1)
public class Telefone extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.Telefone.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "telefone_id")
    private Long id;

    @Column(name = "numero")
    @NotNull(message = "{numero.null}")
    @Size(min = 10, max = 20, message = "{numero.size}")
    private String numero;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{tipo.null}")
    private TipoTelefone tipo;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @JsonIgnore
    public boolean isComercial() {
        return TipoTelefone.COMERCIAL.equals(tipo);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Telefone telefone = (Telefone) o;

        return Objects.equals(id, telefone.id);
    }

    @Override
    public int hashCode() {
        return 1941305906;
    }
}