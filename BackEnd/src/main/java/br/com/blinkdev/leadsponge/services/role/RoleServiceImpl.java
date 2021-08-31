package br.com.blinkdev.leadsponge.services.role;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.models.role.Role;
import br.com.blinkdev.leadsponge.models.role.RoleFilter;
import br.com.blinkdev.leadsponge.models.role.RoleResumo;
import br.com.blinkdev.leadsponge.repository.role.RoleRepository;
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
