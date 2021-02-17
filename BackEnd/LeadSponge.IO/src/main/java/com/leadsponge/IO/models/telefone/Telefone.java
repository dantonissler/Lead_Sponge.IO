package com.leadsponge.IO.models.telefone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.contato.Contato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "telefones")
@TableGenerator(name = "telefone_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "telefones", allocationSize = 1)
public class Telefone extends UserDateAudit {

    @Id
    @Column(name = "id")
    @JsonView(View.Telefone.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "telefone_id")
    private Long id;

    @Column(name = "numero")
    @NotNull(message = "{telefone.numero.null}")
    @Size(min = 11, max = 11, message = "{telefone.numero.size}")
    private String numero;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @JsonIgnore
    public boolean isComercial() {
        return TipoTelefone.COMERCIAL.equals(tipo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}