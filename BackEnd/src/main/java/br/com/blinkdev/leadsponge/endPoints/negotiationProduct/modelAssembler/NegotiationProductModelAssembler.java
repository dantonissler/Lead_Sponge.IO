package br.com.blinkdev.leadsponge.endPoints.negotiationProduct.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.entity.NegotiationProductEntity;
import br.com.blinkdev.leadsponge.endPoints.negotiationProduct.model.NegotiationProductModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class NegotiationProductModelAssembler extends RepresentationModelAssemblerSupport<NegotiationProductEntity, NegotiationProductModel> {
    @Autowired
    private ModelMapper modelMapper;

    public NegotiationProductModelAssembler() {
        super(NegotiationProductEntity.class, NegotiationProductModel.class);
    }

    @Override
    public NegotiationProductModel toModel(NegotiationProductEntity entity) {
        NegotiationProductModel negotiationProductModel = modelMapper.map(entity, NegotiationProductModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return negotiationProductModel;
    }

    @Override
    public CollectionModel<NegotiationProductModel> toCollectionModel(Iterable<? extends NegotiationProductEntity> entities) {
        CollectionModel<NegotiationProductModel> negotiationProductModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return negotiationProductModels;
    }
}
