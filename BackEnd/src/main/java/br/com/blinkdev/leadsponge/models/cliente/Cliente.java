package br.com.blinkdev.leadsponge.models.cliente;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.contato.Contato;
import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@TableGenerator(name = "cliente_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "clientes", allocationSize = 1)
public class Cliente extends UserDateAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @JsonView(View.Cliente.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "cliente_id")
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "{nome.null}")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nome;

    @Size(max = 1024, message = "{descricao.max}")
    private String url;

    @Size(max = 1024, message = "{descricao.max}")
    private String resumo;

    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Contato> contato = new ArrayList<>();

    @JsonIgnoreProperties(value = {"cliente", "clientes", "clientesSeguidos", "roles", "tarefas"})
    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Negociacao> negociacoes;

    @ManyToMany
    @JsonIgnoreProperties("clientes")
    @JoinTable(name = "segmentos_clientes", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segmento_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Segmento> segmentos = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @JoinTable(name = "seguidores_clientes_seguidos", joinColumns = @JoinColumn(name = "clientes_seguidos_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "seguidor_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Usuario> seguidores = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;

        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return 1874664051;
    }
}
