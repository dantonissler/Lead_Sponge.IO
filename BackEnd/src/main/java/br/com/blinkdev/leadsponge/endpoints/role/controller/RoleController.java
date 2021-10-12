package br.com.blinkdev.leadsponge.endpoints.role.controller;

import br.com.blinkdev.leadsponge.endpoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endpoints.role.model.RoleModel;
import br.com.blinkdev.leadsponge.endpoints.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "roles", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Roles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class RoleController {
    private final RoleService roleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search for roles with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public PagedModel<RoleModel> searchWithFilters(RoleFilter usuarioFilter, Pageable pageable) {
        return roleService.searchWithFilters(usuarioFilter, pageable);
    }
}
