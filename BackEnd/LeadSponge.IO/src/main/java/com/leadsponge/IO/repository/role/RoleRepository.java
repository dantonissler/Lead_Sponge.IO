package com.leadsponge.IO.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsponge.IO.models.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryQuery {
}
