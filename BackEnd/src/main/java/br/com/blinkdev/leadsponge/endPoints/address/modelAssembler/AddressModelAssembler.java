package br.com.blinkdev.leadsponge.endPoints.address.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.address.entity.AddressEntity;
import br.com.blinkdev.leadsponge.endPoints.address.model.AddressModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class AddressModelAssembler extends RepresentationModelAssemblerSupport<AddressEntity, AddressModel> {
    @Autowired
    private ModelMapper modelMapper;

    public AddressModelAssembler() {
        super(AddressEntity.class, AddressModel.class);
    }

    @Override
    public AddressModel toModel(AddressEntity entity) {
        AddressModel addressMode = modelMapper.map(entity, AddressModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return addressMode;
    }

    @Override
    public CollectionModel<AddressModel> toCollectionModel(Iterable<? extends AddressEntity> entities) {
        CollectionModel<AddressModel> addressModes = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return addressModes;
    }
}
