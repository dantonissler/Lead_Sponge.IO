package br.com.blinkdev.leadsponge.end_points.user.entity;

import br.com.blinkdev.leadsponge.end_points.View;
import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.end_points.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.utils.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@TableGenerator(name = "user_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "user", allocationSize = 1)
public class UserEntity extends UserDateAudit implements UserDetails {

    @Id
    @JsonView(View.Usuario.class)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_id")
    private Long id;

    @Column(unique = true)
    @NotNull(message = "{login.null}")
    @Size(min = 4, max = 32, message = "{login.size}")
    private String username;

    @Column(name = "nome_completo")
    @Size(min = 4, max = 50, message = "{nome.size}")
    private String fullName;

    @Email(message = "{email.not.valid}")
    @NotBlank(message = "{email.not.blank}")
    private String email;

    @Size(min = 6, max = 150, message = "{senha.size}")
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    private String photo;

    private String urlPhoto;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("usuarios")
    @JoinTable(name = "roles_usuarios", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roles;

    @JsonIgnoreProperties("usuario")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TaskEntity> tasks;

    @ManyToMany(mappedBy = "seguidores", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("seguidores")
    @ToString.Exclude
    private List<CustomerEntity> customers;

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

    public UserEntity(String username, String nomeCompleto, String email, String password, String confirmarPassword,
                      Boolean enabled, String foto, String urlFoto, Set<RoleEntity> roles) {
        this.username = username;
        this.fullName = nomeCompleto;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmarPassword;
        this.enabled = enabled;
        this.photo = foto;
        this.urlPhoto = urlFoto;
        this.roles = roles;
    }
}
