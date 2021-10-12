package br.com.blinkdev.leadsponge.endpoints.role.repository;

import br.com.blinkdev.leadsponge.endpoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endpoints.role.filter.RoleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryQuery {
    Page<RoleEntity> filtrar(RoleFilter produtoFilter, Pageable pageable);
}
