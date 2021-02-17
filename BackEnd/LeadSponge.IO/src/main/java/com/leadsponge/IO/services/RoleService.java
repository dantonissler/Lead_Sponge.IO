package com.leadsponge.IO.services;

import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.Filter.RoleFilter;
import com.leadsponge.IO.repository.projection.RoleResumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Page<Role> filtrar(RoleFilter roleFilter, Pageable pageable);

    Page<RoleResumo> resumir(RoleFilter roleFilter, Pageable pageable);
}
