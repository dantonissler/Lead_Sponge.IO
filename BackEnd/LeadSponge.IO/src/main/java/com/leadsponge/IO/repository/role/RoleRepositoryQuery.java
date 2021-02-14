package com.leadsponge.IO.repository.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.Filter.RoleFilter;

public interface RoleRepositoryQuery {
	Page<Role> filtrar(RoleFilter produtoFilter, Pageable pageable);
}
