package br.com.blinkdev.leadsponge.endPoints.role.controller;

import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endPoints.role.model.RoleModel;
import br.com.blinkdev.leadsponge.endPoints.role.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
class RoleController {

    @Autowired
    private final RoleService roleService;

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public Page<RoleModel> list(RoleFilter roleFilter, Pageable pageable) {
        return roleService.resumir(roleFilter, pageable);
    }

    @GetMapping(value = {""})
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public Page<RoleEntity> pesquisar(RoleFilter usuarioFilter, Pageable pageable) {
        return roleService.filtrar(usuarioFilter, pageable);
    }
}
