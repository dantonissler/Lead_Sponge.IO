package br.com.blinkdev.leadsponge.end_points.customer.model_assembler;

import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.customer.model.CustomerModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<CustomerEntity, CustomerModel> {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerModelAssembler() {
        super(CustomerEntity.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(CustomerEntity entity) {
        CustomerModel customerModel = modelMapper.map(entity, CustomerModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return customerModel;
    }

    @Override
    public CollectionModel<CustomerModel> toCollectionModel(Iterable<? extends CustomerEntity> entities) {
        CollectionModel<CustomerModel> customerModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return customerModels;
    }
}
