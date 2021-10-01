package br.com.blinkdev.leadsponge.endPoints.task.modelAssembler;

import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endPoints.task.model.TaskModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TaskModelAssembler extends RepresentationModelAssemblerSupport<TaskEntity, TaskModel> {
    @Autowired
    private ModelMapper modelMapper;

    public TaskModelAssembler() {
        super(TaskEntity.class, TaskModel.class);
    }

    @Override
    public TaskModel toModel(TaskEntity entity) {
        TaskModel taskModel = modelMapper.map(entity, TaskModel.class);
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).getById(entity.getId())).withSelfRel().withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).save(null, null)).withRel("save").withType("POST"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).patch(null, entity.getId(), null)).withRel("updatePatch").withType("PATCH"));
//        campanhaModel.add(linkTo(methodOn(CampaignController.class).delete(entity.getId())).withRel("delete").withType("DELETE"));
        return taskModel;
    }

    @Override
    public CollectionModel<TaskModel> toCollectionModel(Iterable<? extends TaskEntity> entities) {
        CollectionModel<TaskModel> taskModels = super.toCollectionModel(entities);
//        campanhaModels.add(linkTo(methodOn(CampaignController.class).searchWithFilters(null, Pageable.unpaged())).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
//        campanhaModels.add(linkTo(methodOn(RootEntryPointController.class).root()).withRel("entry_Point").withType("GET"));
        return taskModels;
    }
}
