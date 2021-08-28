package br.com.blinkdev.leadsponge.repository.role;

import br.com.blinkdev.leadsponge.models.role.Role;
import br.com.blinkdev.leadsponge.repository.Filter.RoleFilter;
import br.com.blinkdev.leadsponge.repository.projection.RoleResumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryQuery {
    Page<Role> filtrar(RoleFilter produtoFilter, Pageable pageable);

    Page<RoleResumo> resumir(RoleFilter roleFilter, Pageable pageable);
}
