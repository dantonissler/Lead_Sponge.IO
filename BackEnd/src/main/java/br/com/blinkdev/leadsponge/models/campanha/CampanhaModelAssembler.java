package br.com.blinkdev.leadsponge.models.campanha;

import br.com.blinkdev.leadsponge.endPoints.CampanhaEndPoint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CampanhaModelAssembler extends RepresentationModelAssemblerSupport<CampanhaEntity, CampanhaModel> {

    public CampanhaModelAssembler() {
        super(CampanhaEndPoint.class, CampanhaModel.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CampanhaModel toModel(CampanhaEntity entity) {
        CampanhaModel campanhaModel = modelMapper.map(entity, CampanhaModel.class);
        campanhaModel.add(linkTo(methodOn(CampanhaEndPoint.class).getCampanhaById(entity.getId())).withSelfRel());
        return campanhaModel;
    }

    @Override
    public CollectionModel<CampanhaModel> toCollectionModel(Iterable<? extends CampanhaEntity> entities) {
        CollectionModel<CampanhaModel> campanhaModels = super.toCollectionModel(entities);
        campanhaModels.add(linkTo(methodOn(CampanhaEndPoint.class).getAllCampanhas()).withSelfRel());
        return campanhaModels;
    }
}
