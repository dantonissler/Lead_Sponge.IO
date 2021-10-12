package br.com.blinkdev.leadsponge.endpoints.role.service;

import br.com.blinkdev.leadsponge.endpoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endpoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endpoints.role.model.RoleModel;
import br.com.blinkdev.leadsponge.endpoints.role.modelAssembler.RoleModelAssembler;
import br.com.blinkdev.leadsponge.endpoints.role.repository.RoleRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl extends ErroMessage implements RoleService {
    private final RoleRepository repository;
    private final RoleModelAssembler roleModelAssembler;
    private final PagedResourcesAssembler<RoleEntity> assembler;


    @Override
    public PagedModel<RoleModel> searchWithFilters(RoleFilter roleFilter, Pageable pageable) {
        return assembler.toModel(repository.filtrar(roleFilter, pageable), roleModelAssembler);
    }

}
