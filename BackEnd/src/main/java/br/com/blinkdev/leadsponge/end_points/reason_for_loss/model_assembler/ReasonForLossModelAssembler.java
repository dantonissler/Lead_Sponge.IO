package br.com.blinkdev.leadsponge.end_points.reason_for_loss.model_assembler;

import br.com.blinkdev.leadsponge.end_points.reason_for_loss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.end_points.reason_for_loss.model.ReasonForLossModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ReasonForLossModelAssembler extends RepresentationModelAssemblerSupport<ReasonForLossEntity, ReasonForLossModel> {
    @Autowired
    private ModelMapper modelMapper;

    public ReasonForLossModelAssembler() {
        super(ReasonForLossEntity.class, ReasonForLossModel.class);
    }

    @Override
    public ReasonForLossModel toModel(ReasonForLossEntity entity) {
        ReasonForLossModel reasonForLossModel = modelMapper.map(entity, ReasonForLossModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return reasonForLossModel;
    }

    @Override
    public CollectionModel<ReasonForLossModel> toCollectionModel(Iterable<? extends ReasonForLossEntity> entities) {
        CollectionModel<ReasonForLossModel> reasonForLossModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return reasonForLossModels;
    }
}
