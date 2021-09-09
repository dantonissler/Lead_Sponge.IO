package br.com.blinkdev.leadsponge.endPoints.contato.entity;

import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contatos")
@TableGenerator(name = "contato_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "contatos", allocationSize = 1)
public class ContatoEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    public ContatoEntity(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "contato_id")
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    @Size(max = 50, message = "{cargo.size}")
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @JsonIgnoreProperties("contato")
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Telefone> telefone;

    @JsonIgnoreProperties("contato")
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<EmailEntity> email;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ContatoEntity contato = (ContatoEntity) o;

        return Objects.equals(id, contato.id);
    }

    @Override
    public int hashCode() {
        return 391263437;
    }
}
