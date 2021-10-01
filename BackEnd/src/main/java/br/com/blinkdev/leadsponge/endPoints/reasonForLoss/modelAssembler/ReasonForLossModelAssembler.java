package br.com.blinkdev.leadsponge.endPoints.reasonForLoss.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endPoints.contact.model.ContactModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class ReasonForLossModelAssembler extends RepresentationModelAssemblerSupport<ContactEntity, ContactModel> {
    @Autowired
    private ModelMapper modelMapper;

    public ReasonForLossModelAssembler() {
        super(ContactEntity.class, ContactModel.class);
    }

    @Override
    public ContactModel toModel(ContactEntity entity) {
        ContactModel campanhaModel = modelMapper.map(entity, ContactModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return campanhaModel;
    }

    @Override
    public CollectionModel<ContactModel> toCollectionModel(Iterable<? extends ContactEntity> entities) {
        CollectionModel<ContactModel> campanhaModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return campanhaModels;
    }
}
