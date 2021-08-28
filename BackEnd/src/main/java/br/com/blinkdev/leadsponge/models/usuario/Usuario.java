package br.com.blinkdev.leadsponge.models.usuario;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.role.Role;
import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@TableGenerator(name = "usuario_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "usuarios", allocationSize = 1)
public class Usuario extends UserDateAudit implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    public Usuario(Long id){
        this.id = id;
    }
    public Usuario(String username, String nomeCompleto, String email, String password, String confirmarPassword,
                   Boolean enabled, String foto, String urlFoto, Set<Role> roles) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.password = password;
        this.confirmarPassword = confirmarPassword;
        this.enabled = enabled;
        this.foto = foto;
        this.urlFoto = urlFoto;
        this.roles = roles;
    }

    @Id
    @Column(name = "id")
    @JsonView(View.Usuario.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "usuario_id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull(message = "{login.null}")
    @Size(min = 4, max = 32, message = "{login.size}")
    private String username;

    @Column(name = "nome_completo")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String nomeCompleto;

    @Column(name = "email")
    @Email(message = "{email.not.valid}")
    @NotBlank(message = "{email.not.blank}")
    private String email;

    @Column(name = "password")
    @Size(min = 6, max = 150, message = "{senha.size}")
    private String password;

    private String foto;

    private String urlFoto;

    @Transient
    private String confirmarPassword;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("usuarios")
    @JoinTable(name = "roles_usuarios", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @JsonIgnoreProperties("usuario")
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Tarefa> tarefas = new ArrayList<>();

    @ManyToMany(mappedBy = "seguidores", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("seguidores")
    @ToString.Exclude
    private List<Cliente> clientesSeguidos;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;

        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return 1225039686;
    }
}
