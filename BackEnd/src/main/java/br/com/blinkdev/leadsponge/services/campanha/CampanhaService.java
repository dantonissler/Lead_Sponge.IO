package br.com.blinkdev.leadsponge.services.campanha;

import br.com.blinkdev.leadsponge.models.campanha.CampanhaEntity;
import br.com.blinkdev.leadsponge.models.campanha.CampanhaFilter;
import br.com.blinkdev.leadsponge.models.campanha.CampanhaModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CampanhaService {
    PagedModel<CampanhaModel> getCampanhaByFilter(CampanhaFilter campanhaFilter, Pageable pageable);

    CollectionModel<CampanhaModel> getAllCampanhas();

    CampanhaModel getCampanhaById(Long id);

    CampanhaEntity salvar(CampanhaEntity campanha);

    CampanhaEntity atualizar(CampanhaEntity campanha);

    CampanhaEntity updatePatch(Long id, Map<Object, Object> fields);

    CampanhaEntity deletar(Long id);
}
