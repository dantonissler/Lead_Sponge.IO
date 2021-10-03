package br.com.blinkdev.leadsponge.endPoints.phone.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.model.PhoneModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PhoneModelAssembler extends RepresentationModelAssemblerSupport<PhoneEntity, PhoneModel> {
    @Autowired
    private ModelMapper modelMapper;

    public PhoneModelAssembler() {
        super(PhoneEntity.class, PhoneModel.class);
    }

    @Override
    public PhoneModel toModel(PhoneEntity entity) {
        PhoneModel phoneModel = modelMapper.map(entity, PhoneModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return phoneModel;
    }

    @Override
    public CollectionModel<PhoneModel> toCollectionModel(Iterable<? extends PhoneEntity> entities) {
        CollectionModel<PhoneModel> phoneModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return phoneModels;
    }
}
