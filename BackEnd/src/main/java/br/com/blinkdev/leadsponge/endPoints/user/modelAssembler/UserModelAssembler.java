package br.com.blinkdev.leadsponge.endPoints.user.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.rootEntryPoint.controller.RootEntryPointController;
import br.com.blinkdev.leadsponge.endPoints.user.controller.UserController;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserEntity entity) {
        UserModel userModel = modelMapper.map(entity, UserModel.class);
        userModel.add(linkTo(methodOn(UserController.class).getById(entity.getId())).withSelfRel().withType("GET"));
        userModel.add(linkTo(methodOn(UserController.class).getAll()).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        userModel.add(linkTo(methodOn(UserController.class).searchWithFilter(null, null)).withRel(IanaLinkRelations.SEARCH_VALUE).withType("GET"));
        userModel.add(linkTo(methodOn(UserController.class).save(null, null)).withRel("save").withType("POST"));
        userModel.add(linkTo(methodOn(UserController.class).update(null, null, null)).withRel("update").withType("PUT"));
        userModel.add(linkTo(methodOn(UserController.class).updatePatch(null, null, null)).withRel("updatePatch").withType("PATCH"));
        userModel.add(linkTo(methodOn(UserController.class).delete(null)).withRel("delete").withType("DELETE"));
        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends UserEntity> entities) {
        CollectionModel<UserModel> campanhaModels = super.toCollectionModel(entities);
        campanhaModels.add(linkTo(methodOn(UserController.class).getAll()).withSelfRel().withType("GET"));
        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return campanhaModels;
    }
}
