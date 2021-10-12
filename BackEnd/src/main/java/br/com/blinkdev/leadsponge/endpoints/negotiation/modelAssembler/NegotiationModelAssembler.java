package br.com.blinkdev.leadsponge.endpoints.negotiation.modelAssembler;

import br.com.blinkdev.leadsponge.endpoints.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.endpoints.negotiation.model.NegotiationModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class NegotiationModelAssembler extends RepresentationModelAssemblerSupport<NegotiationEntity, NegotiationModel> {

    @Autowired
    private ModelMapper modelMapper;

    public NegotiationModelAssembler() {
        super(NegotiationEntity.class, NegotiationModel.class);
    }

    @Override
    public NegotiationModel toModel(NegotiationEntity entity) {
        NegotiationModel negotiationModel = modelMapper.map(entity, NegotiationModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return negotiationModel;
    }

    @Override
    public CollectionModel<NegotiationModel> toCollectionModel(Iterable<? extends NegotiationEntity> entities) {
        CollectionModel<NegotiationModel> negotiationModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return negotiationModels;
    }
}
