package br.com.blinkdev.leadsponge.endPoints.campanha.service;

import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampanhaFilter;
import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampanhaModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CampanhaService {
    PagedModel<CampanhaModel> searchCampanha(CampanhaFilter campanhaFilter, Pageable pageable);

    CollectionModel<CampanhaModel> getAllCampanhas();

    CampanhaModel getCampanhaById(Long id);

    CampanhaEntity salvar(CampanhaEntity campanha);

    CampanhaEntity update(CampanhaEntity campanha);

    CampanhaEntity updatePatch(Long id, Map<Object, Object> fields);

    CampanhaEntity delete(Long id);
}
