package br.com.blinkdev.leadsponge.end_points.role.service;

import br.com.blinkdev.leadsponge.end_points.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.end_points.role.model.RoleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    PagedModel<RoleModel> searchWithFilters(RoleFilter roleFilter, Pageable pageable);
}
