package br.com.blinkdev.leadsponge.end_points.user.modelAssembler;

import br.com.blinkdev.leadsponge.end_points.root_entry_point.controller.RootEntryPointController;
import br.com.blinkdev.leadsponge.end_points.user.controller.UserController;
import br.com.blinkdev.leadsponge.end_points.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.end_points.user.model.UserModel;
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
        userModel.add(linkTo(methodOn(UserController.class).searchWithFilter(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        userModel.add(linkTo(methodOn(UserController.class).save(null, null)).withRel("save").withType("POST"));
        userModel.add(linkTo(methodOn(UserController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
        userModel.add(linkTo(methodOn(UserController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends UserEntity> entities) {
        CollectionModel<UserModel> campanhaModels = super.toCollectionModel(entities);
        campanhaModels.add(linkTo(methodOn(UserController.class).searchWithFilter(null, Pageable.unpaged())).withSelfRel().withType("GET"));
        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return campanhaModels;
    }
}
