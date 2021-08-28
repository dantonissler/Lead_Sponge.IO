package br.com.blinkdev.leadsponge.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blinkdev.leadsponge.models.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryQuery {
}
