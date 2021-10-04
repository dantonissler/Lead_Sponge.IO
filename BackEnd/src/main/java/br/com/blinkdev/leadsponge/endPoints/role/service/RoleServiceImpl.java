package br.com.blinkdev.leadsponge.endPoints.role.service;

import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endPoints.role.model.RoleModel;
import br.com.blinkdev.leadsponge.endPoints.role.modelAssembler.RoleModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.role.repository.RoleRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ErroMessage implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleModelAssembler roleModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RoleEntity> assembler;


    @Override
    public PagedModel<RoleModel> searchWithFilters(RoleFilter roleFilter, Pageable pageable) {
        return assembler.toModel(repository.filtrar(roleFilter, pageable), roleModelAssembler);
    }

}
