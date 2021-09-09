package br.com.blinkdev.leadsponge.endPoints.segmento.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "segmentos")
@TableGenerator(name = "segmento_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "segmentos", allocationSize = 1)
public class SegmentoEntity extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    public SegmentoEntity(Long id) {
        this.id = id;
    }

    public SegmentoEntity(String nome) {
        this.nome = nome;
    }

    @Id
    @Column(name = "id")
    @JsonView(View.Segmento.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "segmento_id")
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    @ManyToMany(mappedBy = "segmentos", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("segmentos")
    @ToString.Exclude
    private List<ClienteEntity> clientes;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SegmentoEntity segmento = (SegmentoEntity) o;

        return Objects.equals(id, segmento.id);
    }

    @Override
    public int hashCode() {
        return 576652681;
    }
}
