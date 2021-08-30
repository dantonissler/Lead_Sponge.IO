package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.role.Role;
import br.com.blinkdev.leadsponge.models.role.RoleFilter;
import br.com.blinkdev.leadsponge.models.role.RoleResumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Page<Role> filtrar(RoleFilter roleFilter, Pageable pageable);

    Page<RoleResumo> resumir(RoleFilter roleFilter, Pageable pageable);
}
