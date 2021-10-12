package br.com.blinkdev.leadsponge.endpoints.customer.entity;

import br.com.blinkdev.leadsponge.endpoints.View;
import br.com.blinkdev.leadsponge.endpoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endpoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endpoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "customer")
@TableGenerator(name = "customer_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "customer", allocationSize = 1)
public class CustomerEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(View.Cliente.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_id")
    private Long id;

    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    @Size(max = 1024, message = "{descricao.max}")
    private String url;

    @Size(max = 1024, message = "{descricao.max}")
    private String resumo;

    @JsonIgnoreProperties("customer")
    @OneToMany(mappedBy = "customer")
    private List<ContactEntity> contact;

    @JsonIgnoreProperties(value = {"cliente", "clientes", "clientesSeguidos", "roles", "tarefas"})
    @OneToMany(mappedBy = "cliente")
    private List<NegotiationEntity> negociacoes;

    @ManyToMany
    @JsonIgnoreProperties("clientes")
    @JoinTable(name = "segmentos_clientes", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segmento_id", referencedColumnName = "id"))
    private List<SegmentEntity> segmentos;

    @ManyToMany
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @JoinTable(name = "seguidores_clientes_seguidos", joinColumns = @JoinColumn(name = "clientes_seguidos_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "seguidor_id", referencedColumnName = "id"))
    private List<UserEntity> seguidores;

    @ManyToOne
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @JoinColumn(name = "responsavel_id")
    private UserEntity responsavel;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerEntity cliente = (CustomerEntity) o;

        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return 1874664051;
    }
}
