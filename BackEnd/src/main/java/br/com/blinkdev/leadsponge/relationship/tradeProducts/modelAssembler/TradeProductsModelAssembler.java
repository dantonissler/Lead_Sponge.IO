package br.com.blinkdev.leadsponge.relationship.tradeProducts.modelAssembler;

import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.model.TradeProductsModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TradeProductsModelAssembler extends RepresentationModelAssemblerSupport<TradeProductsEntity, TradeProductsModel> {
    @Autowired
    private ModelMapper modelMapper;

    public TradeProductsModelAssembler() {
        super(TradeProductsEntity.class, TradeProductsModel.class);
    }

    @Override
    public TradeProductsModel toModel(TradeProductsEntity entity) {
        TradeProductsModel negotiationProductModel = modelMapper.map(entity, TradeProductsModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return negotiationProductModel;
    }

    @Override
    public CollectionModel<TradeProductsModel> toCollectionModel(Iterable<? extends TradeProductsEntity> entities) {
        CollectionModel<TradeProductsModel> negotiationProductModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return negotiationProductModels;
    }
}
