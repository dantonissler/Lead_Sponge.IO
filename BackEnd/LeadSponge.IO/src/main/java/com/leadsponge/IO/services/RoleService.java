package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.Filter.RoleFilter;

@Service
public interface RoleService {

	Role detalhar(Long id);

	Page<Role> filtrar(RoleFilter roleFilter, Pageable pageable);

}
