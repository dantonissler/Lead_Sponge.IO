package com.leadsponge.IO.models.role;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leadsponge.IO.models.usuario.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "nome" }) })
@TableGenerator(name = "role_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "roles", allocationSize = 1, initialValue = 0)
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "role_id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@ManyToMany(mappedBy = "roles")
	@JsonBackReference("roles")
	private Set<Usuario> usuarios;

	@Override
	@JsonIgnore
	public String getAuthority() {
		return this.nome;
	}

}
