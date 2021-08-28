package br.com.blinkdev.leadsponge.models.cliente;

import br.com.blinkdev.leadsponge.models.contato.Contato;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@TableGenerator(name = "cliente_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "clientes", allocationSize = 1)
public class Cliente extends UserDateAudit {

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
    private List<Contato> contato = new ArrayList<>();

    @JsonIgnoreProperties(value = {"cliente", "clientes", "clientesSeguidos", "roles", "tarefas"})
    @OneToMany(mappedBy = "cliente")
    private List<Negociacao> negociacoes;

    @ManyToMany
    @JsonIgnoreProperties("clientes")
    @JoinTable(name = "segmentos_clientes", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segmento_id", referencedColumnName = "id"))
    private List<Segmento> segmentos = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @JoinTable(name = "seguidores_clientes_seguidos", joinColumns = @JoinColumn(name = "clientes_seguidos_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "seguidor_id", referencedColumnName = "id"))
    private List<Usuario> seguidores = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
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
