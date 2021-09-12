package br.com.blinkdev.leadsponge.endPoints.phone.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.enumeration.TypePhone;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
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
@Table(name = "phone")
@TableGenerator(name = "phone_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "phone", allocationSize = 1)
public class PhoneEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(View.Telefone.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "phone_id")
    private Long id;

    @NotNull(message = "{numero.null}")
    @Size(min = 10, max = 20, message = "{numero.size}")
    private String numero;

    @NotNull
    @Size(min = 2, max = 3)
    @Column(name = "codigo_area")
    private String codigoArea;

    @Size(min = 3, max = 5)
    @Column(name = "codigo_pais")
    private String codigoPais;

    private Boolean temWhatsapp;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{tipo.null}")
    private TypePhone type;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private ContactEntity contato;

    @JsonIgnore
    public boolean isComercial() {
        return TypePhone.COMERCIAL.equals(type);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhoneEntity telefone = (PhoneEntity) o;

        return Objects.equals(id, telefone.id);
    }

    @Override
    public int hashCode() {
        return 1941305906;
    }
}