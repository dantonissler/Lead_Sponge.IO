package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.models.role.Role;
import br.com.blinkdev.leadsponge.repository.Filter.RoleFilter;
import br.com.blinkdev.leadsponge.repository.projection.RoleResumo;
import br.com.blinkdev.leadsponge.repository.role.RoleRepository;
import br.com.blinkdev.leadsponge.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ErroMessage implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Page<Role> filtrar(RoleFilter roleFilter, Pageable pageable) {
        return repository.filtrar(roleFilter, pageable);
    }

    @Override
    public Page<RoleResumo> resumir(RoleFilter roleFilter, Pageable pageable) {
        return repository.resumir(roleFilter, pageable);
    }

}
