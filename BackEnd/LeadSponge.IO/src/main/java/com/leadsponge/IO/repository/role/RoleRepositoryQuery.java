package com.leadsponge.IO.repository.role;

import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.Filter.RoleFilter;
import com.leadsponge.IO.repository.projection.RoleResumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryQuery {
    Page<Role> filtrar(RoleFilter produtoFilter, Pageable pageable);

    Page<RoleResumo> resumir(RoleFilter roleFilter, Pageable pageable);
}
