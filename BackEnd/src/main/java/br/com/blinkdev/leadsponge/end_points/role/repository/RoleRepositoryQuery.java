package br.com.blinkdev.leadsponge.end_points.role.repository;

import br.com.blinkdev.leadsponge.end_points.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.end_points.role.filter.RoleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryQuery {
    Page<RoleEntity> filtrar(RoleFilter produtoFilter, Pageable pageable);
}
