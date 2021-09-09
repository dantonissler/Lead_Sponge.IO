package br.com.blinkdev.leadsponge.endPoints.role.service;

import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endPoints.role.model.RoleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Page<RoleEntity> filtrar(RoleFilter roleFilter, Pageable pageable);

    Page<RoleModel> resumir(RoleFilter roleFilter, Pageable pageable);
}
