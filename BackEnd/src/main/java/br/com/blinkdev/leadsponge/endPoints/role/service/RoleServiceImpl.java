package br.com.blinkdev.leadsponge.endPoints.role.service;

import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endPoints.role.model.RoleModel;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ErroMessage implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Page<RoleEntity> filtrar(RoleFilter roleFilter, Pageable pageable) {
        return repository.filtrar(roleFilter, pageable);
    }

    @Override
    public Page<RoleModel> resumir(RoleFilter roleFilter, Pageable pageable) {
        return repository.resumir(roleFilter, pageable);
    }

}
