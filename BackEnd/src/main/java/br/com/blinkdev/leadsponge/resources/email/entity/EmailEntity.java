package br.com.blinkdev.leadsponge.resources.email.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.end_points.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
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
@TableGenerator(name = "email_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "emails", allocationSize = 1)
public class EmailEntity extends UserDateAudit implements Serializable {

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
    private ContactEntity contato;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmailEntity email = (EmailEntity) o;

        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return 459610384;
    }
}
