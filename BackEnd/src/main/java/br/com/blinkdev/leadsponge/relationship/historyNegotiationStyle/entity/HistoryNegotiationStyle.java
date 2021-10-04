package br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity;

import br.com.blinkdev.leadsponge.endPoints.View;
import br.com.blinkdev.leadsponge.endPoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

// TODO: da para refatorar isso aqui fazer uma ligação many to many da forma correta
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history_negotiation_style")
@TableGenerator(name = "history_negotiation_style_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "history_negotiation_style", allocationSize = 1)
public class HistoryNegotiationStyle extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(View.HistEstagioNegociacao.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "history_negotiation_style_id")
    private Long id;

    @NotNull
    @Column(name = "id_estagio")
    private String idEstagio;

    @NotNull
    private String apelido;

    @NotNull
    @Column(name = "data_mudanca")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate dataMudanca;

    @ManyToOne
    @JoinColumn(name = "negociacao_id")
    private NegotiationEntity negociacao;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HistoryNegotiationStyle other = (HistoryNegotiationStyle) obj;
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
