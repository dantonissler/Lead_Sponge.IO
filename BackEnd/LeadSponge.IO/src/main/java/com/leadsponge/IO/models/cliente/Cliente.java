package com.leadsponge.IO.models.cliente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.models.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
@TableGenerator(name = "cliente_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "clientes", allocationSize = 1, initialValue = 0)
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

    @Valid
    @JsonIgnoreProperties("cliente")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Contato> contato = new ArrayList<>();

    @Valid
    @JsonIgnoreProperties(value = {"cliente", "clientes", "clientesSeguidos", "roles", "tarefas"})
    @OneToMany(mappedBy = "cliente")
    private List<Negociacao> negociacoes;

    @Valid
    @ManyToMany
    @JsonIgnoreProperties("clientes")
    @JoinTable(name = "segmentos_clientes", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segmento_id", referencedColumnName = "id"))
    private List<Segmento> segmentos = new ArrayList<>();

    @Valid
    @ManyToMany
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @JoinTable(name = "seguidores_clientesSeguidos", joinColumns = @JoinColumn(name = "clientesSeguidos_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "seguidor_id", referencedColumnName = "id"))
    private List<Usuario> seguidores = new ArrayList<>();

    @Valid
    @JsonIgnoreProperties(value = {"clientes", "clientesSeguidos", "roles", "tarefas"})
    @ManyToOne
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
