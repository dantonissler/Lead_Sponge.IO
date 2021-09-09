package br.com.blinkdev.leadsponge.endPoints.historicoEstagioNegociacao.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hist_estagio_negociacao")
@TableGenerator(name = "hist_estagio_negociacao_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "hist_estagio_negociacao", allocationSize = 1)
public class HistEstagioNegociacao extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.HistEstagioNegociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hist_estagio_negociacao_id")
    private Long id;

    @NotNull
    @Column(name = "id_estagio")
    private String idEstagio;

    @NotNull
    @Column(name = "apelido")
    private String apelido;

    @NotNull
    @Column(name = "data_mudanca")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate dataMudanca;

    @ManyToOne
    @JoinColumn(name = "negociacao_id")
    private NegociacaoEntity negociacao;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HistEstagioNegociacao other = (HistEstagioNegociacao) obj;
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
