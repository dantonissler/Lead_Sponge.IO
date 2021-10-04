package br.com.blinkdev.leadsponge.resources.address.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@TableGenerator(name = "address_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "address", allocationSize = 1)
public class AddressEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(View.Telefone.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "phone_id")
    private Long id;

    @NotEmpty(message = "{zip_code.not_empty}")
    @Size(max = 9, message = "{zip_code.size}")
    @Column(name = "zip_code", length = 9)
    private String zipCode;

    @NotEmpty(message = "{public_place.not_empty}")
    @Column(name = "public_place")
    private String publicPlace;

    @NotEmpty(message = "{district.not_empty}")
    private String district;

    @NotEmpty(message = "{location.not_empty}")
    private String location;

    @NotEmpty(message = "{uf.not_empty}")
    @Size(max = 2, message = "{uf.size}")
    @Column(length = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private ContactEntity contact;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AddressEntity telefone = (AddressEntity) o;

        return Objects.equals(id, telefone.id);
    }

    @Override
    public int hashCode() {
        return 1941305906;
    }
}