package com.leadsponge.IO.models.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.leadsponge.IO.models.View;
import com.leadsponge.IO.models.audit.UserDateAudit;
import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.models.tarefa.Tarefa;

import lombok.Data;

@Entity
@Data
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@TableGenerator(name = "usuario_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "usuarios", allocationSize = 1, initialValue = 0)
public class Usuario extends UserDateAudit implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	public Usuario() {
	}

	public Usuario(String username, String nomeCompleto, String email, String password, String confirmarPassword,
			boolean enabled) {
		super();
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.password = password;
		this.confirmarPassword = confirmarPassword;
		this.enabled = enabled;
	}

	@Id
	@Column(name = "id")
	@JsonView(View.Usuario.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "usuario_id")
	private Long id;

	@Column(name = "username", unique = true)
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

	@Transient
	private String confirmarPassword;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("usuarios")
	@JoinTable(name = "roles_usuarios", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	@OneToMany(mappedBy = "usuarioTarefa", fetch = FetchType.EAGER)
//	@JsonManagedReference("usuarioTarefa")
	private List<Tarefa> tarefaUsuario  = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getConfirmarPassword() {
		return confirmarPassword;
	}

	public void setConfirmarPassword(String confirmarPassword) {
		this.confirmarPassword = confirmarPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public List<Tarefa> getTarefas() {
		return tarefaUsuario;
	}

	public void setTarefas(List<Tarefa> tarefaUsuario) {
		this.tarefaUsuario = tarefaUsuario;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
