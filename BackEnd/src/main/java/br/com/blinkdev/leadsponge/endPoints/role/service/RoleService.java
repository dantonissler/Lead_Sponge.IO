package br.com.blinkdev.leadsponge.endPoints.role.service;

import br.com.blinkdev.leadsponge.endPoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endPoints.role.model.RoleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    PagedModel<RoleModel> searchWithFilters(RoleFilter roleFilter, Pageable pageable);
}
