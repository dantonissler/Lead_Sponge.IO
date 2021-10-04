package br.com.blinkdev.leadsponge.resources.email.modelAssembler;

import br.com.blinkdev.leadsponge.resources.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.resources.email.model.EmailModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class EmailModelAssembler extends RepresentationModelAssemblerSupport<EmailEntity, EmailModel> {

    @Autowired
    private ModelMapper modelMapper;

    public EmailModelAssembler() {
        super(EmailEntity.class, EmailModel.class);
    }

    @Override
    public EmailModel toModel(EmailEntity entity) {
        EmailModel emailModel = modelMapper.map(entity, EmailModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return emailModel;
    }

    @Override
    public CollectionModel<EmailModel> toCollectionModel(Iterable<? extends EmailEntity> entities) {
        CollectionModel<EmailModel> emailModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return emailModels;
    }
}
