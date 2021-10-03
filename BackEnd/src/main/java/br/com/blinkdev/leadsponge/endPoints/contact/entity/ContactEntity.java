package br.com.blinkdev.leadsponge.endPoints.contact.entity;

import br.com.blinkdev.leadsponge.endPoints.address.entity.AddressEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
@TableGenerator(name = "contact_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "contact", allocationSize = 1)
public class ContactEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "contact_id")
    private Long id;

    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    @Size(max = 50, message = "{cargo.size}")
    private String cargo;

    @JsonIgnoreProperties("contato")
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL)
    private List<PhoneEntity> phone;

    @JsonIgnoreProperties("contato")
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL)
    private List<EmailEntity> email;

    @JsonIgnoreProperties("contact")
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<AddressEntity> address;

    @JsonIgnoreProperties(value = {"contact", "negociacoes"})// TODO: entender o por que isso não funciona.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    public ContactEntity(Long id) {
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ContactEntity contato = (ContactEntity) o;

        return Objects.equals(id, contato.id);
    }

    @Override
    public int hashCode() {
        return 391263437;
    }
}
