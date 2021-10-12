package br.com.blinkdev.leadsponge.endpoints.segment.modelAssembler;

import br.com.blinkdev.leadsponge.endpoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endpoints.segment.model.SegmentModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class SegmentModelAssembler extends RepresentationModelAssemblerSupport<SegmentEntity, SegmentModel> {
    @Autowired
    private ModelMapper modelMapper;

    public SegmentModelAssembler() {
        super(SegmentEntity.class, SegmentModel.class);
    }

    @Override
    public SegmentModel toModel(SegmentEntity entity) {
        SegmentModel SegmentModel = modelMapper.map(entity, SegmentModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return SegmentModel;
    }

    @Override
    public CollectionModel<SegmentModel> toCollectionModel(Iterable<? extends SegmentEntity> entities) {
        CollectionModel<SegmentModel> SegmentModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return SegmentModels;
    }
}
