package br.com.blinkdev.leadsponge.endPoints.campanha.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.campanha.controller.CampanhaController;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampanhaModel;
import br.com.blinkdev.leadsponge.endPoints.rootEntryPoint.controller.RootEntryPointController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CampanhaModelAssembler extends RepresentationModelAssemblerSupport<CampanhaEntity, CampanhaModel> {

    public CampanhaModelAssembler() {
        super(CampanhaController.class, CampanhaModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CampanhaModel toModel(CampanhaEntity entity) {
        CampanhaModel campanhaModel = modelMapper.map(entity, CampanhaModel.class);
        campanhaModel.add(linkTo(methodOn(CampanhaController.class).getCampanhaById(entity.getId())).withSelfRel().withType("GET"));
        campanhaModel.add(linkTo(methodOn(CampanhaController.class).searchCampanha(null, null)).withRel(IanaLinkRelations.SEARCH_VALUE).withType("GET"));
        campanhaModel.add(linkTo(methodOn(CampanhaController.class).getAllCampanhas()).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        campanhaModel.add(linkTo(methodOn(CampanhaController.class).save(null, null)).withRel("save").withType("POST"));
        campanhaModel.add(linkTo(methodOn(CampanhaController.class).update(null, null)).withRel("update").withType("PUT"));
        campanhaModel.add(linkTo(methodOn(CampanhaController.class).update(null, null)).withRel("updatePatch").withType("PATCH"));
        campanhaModel.add(linkTo(methodOn(CampanhaController.class).delete(null)).withRel("delete").withType("DELETE"));
        return campanhaModel;
    }

    @Override
    public CollectionModel<CampanhaModel> toCollectionModel(Iterable<? extends CampanhaEntity> entities) {
        CollectionModel<CampanhaModel> campanhaModels = super.toCollectionModel(entities);
        campanhaModels.add(linkTo(methodOn(CampanhaController.class).getAllCampanhas()).withSelfRel().withType("GET"));
        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return campanhaModels;
    }
}
