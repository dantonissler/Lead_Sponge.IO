package br.com.blinkdev.leadsponge.endPoints.negotiationStyle.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationStyle.model.NegotiationStyleModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class NegotiationStyleModelAssembler extends RepresentationModelAssemblerSupport<NegotiationStyleEntity, NegotiationStyleModel> {
    @Autowired
    private ModelMapper modelMapper;

    public NegotiationStyleModelAssembler() {
        super(NegotiationStyleEntity.class, NegotiationStyleModel.class);
    }

    @Override
    public NegotiationStyleModel toModel(NegotiationStyleEntity entity) {
        NegotiationStyleModel negotiationStyleModel = modelMapper.map(entity, NegotiationStyleModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return negotiationStyleModel;
    }

    @Override
    public CollectionModel<NegotiationStyleModel> toCollectionModel(Iterable<? extends NegotiationStyleEntity> entities) {
        CollectionModel<NegotiationStyleModel> negotiationStyleModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return negotiationStyleModels;
    }
}
