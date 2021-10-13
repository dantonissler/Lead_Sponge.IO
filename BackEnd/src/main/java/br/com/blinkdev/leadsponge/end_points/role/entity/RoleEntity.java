package br.com.blinkdev.leadsponge.end_points.role.entity;

import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@TableGenerator(name = "role_id", table = "identifier_table", pkColumnName = "name", valueColumnName = "identifier", pkColumnValue = "roles", allocationSize = 1)
public class RoleEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "role_id")
    private Long id;

    @Size(min = 4, max = 100, message = "{produto.nome.size}")
    @NotNull(message = "{nome.null}")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference("roles")
    @ToString.Exclude
    private Set<UserEntity> users;

    public RoleEntity(String name) {
        this.name = name;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.name;
    }
}
