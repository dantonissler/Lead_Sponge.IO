package br.com.blinkdev.leadsponge.endPoints.campanha.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.campanha.controller.CampaignController;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampaignModel;
import br.com.blinkdev.leadsponge.endPoints.rootEntryPoint.controller.RootEntryPointController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CampaignModelAssembler extends RepresentationModelAssemblerSupport<CampaignEntity, CampaignModel> {

    public CampaignModelAssembler() {
        super(CampaignController.class, CampaignModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CampaignModel toModel(CampaignEntity entity) {
        CampaignModel campanhaModel = modelMapper.map(entity, CampaignModel.class);
        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, null, null)).withRel("updatePatch").withType("PATCH"));
        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(null)).withRel("delete").withType("DELETE"));
        return campanhaModel;
    }

    @Override
    public CollectionModel<CampaignModel> toCollectionModel(Iterable<? extends CampaignEntity> entities) {
        CollectionModel<CampaignModel> campanhaModels = super.toCollectionModel(entities);
        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return campanhaModels;
    }
}
