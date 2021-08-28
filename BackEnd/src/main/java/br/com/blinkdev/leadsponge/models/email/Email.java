package br.com.blinkdev.leadsponge.models.email;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.contato.Contato;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emails")
@TableGenerator(name = "email_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "emails", allocationSize = 1)
public class Email extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.Email.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "email_id")
    private Long id;

    @NotEmpty(message = "{email.null}")
    @javax.validation.constraints.Email(message = "{email.not.valid}")
    private String email;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Email email = (Email) o;

        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return 459610384;
    }
}
