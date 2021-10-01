package br.com.blinkdev.leadsponge.endPoints.negotiationSource.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.negotiationSource.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationSource.model.NegotiationSourceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class NegotiationSourceModelAssembler extends RepresentationModelAssemblerSupport<NegotiationSourceEntity, NegotiationSourceModel> {
    @Autowired
    private ModelMapper modelMapper;

    public NegotiationSourceModelAssembler() {
        super(NegotiationSourceEntity.class, NegotiationSourceModel.class);
    }

    @Override
    public NegotiationSourceModel toModel(NegotiationSourceEntity entity) {
        NegotiationSourceModel negotiationSourceModel = modelMapper.map(entity, NegotiationSourceModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return negotiationSourceModel;
    }

    @Override
    public CollectionModel<NegotiationSourceModel> toCollectionModel(Iterable<? extends NegotiationSourceEntity> entities) {
        CollectionModel<NegotiationSourceModel> negotiationSourceModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return negotiationSourceModels;
    }
}
