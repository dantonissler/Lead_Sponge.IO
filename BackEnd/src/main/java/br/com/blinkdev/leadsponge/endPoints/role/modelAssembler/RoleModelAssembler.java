package br.com.blinkdev.leadsponge.endPoints.role.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.role.model.RoleModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class RoleModelAssembler extends RepresentationModelAssemblerSupport<RoleEntity, RoleModel> {
    @Autowired
    private ModelMapper modelMapper;

    public RoleModelAssembler() {
        super(RoleEntity.class, RoleModel.class);
    }

    @Override
    public RoleModel toModel(RoleEntity entity) {
        RoleModel roleModel = modelMapper.map(entity, RoleModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return roleModel;
    }

    @Override
    public CollectionModel<RoleModel> toCollectionModel(Iterable<? extends RoleEntity> entities) {
        CollectionModel<RoleModel> roleModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return roleModels;
    }
}
