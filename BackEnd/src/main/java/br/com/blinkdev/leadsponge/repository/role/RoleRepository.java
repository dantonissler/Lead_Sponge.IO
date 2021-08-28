package br.com.blinkdev.leadsponge.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blinkdev.leadsponge.models.role.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryQuery {
}
