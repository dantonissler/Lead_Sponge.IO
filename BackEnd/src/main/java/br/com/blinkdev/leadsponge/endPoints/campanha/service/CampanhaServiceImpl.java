package br.com.blinkdev.leadsponge.endPoints.campanha.service;

import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampanhaFilter;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampanhaModel;
import br.com.blinkdev.leadsponge.endPoints.campanha.modelAssembler.CampanhaModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.campanha.repository.CampanhaRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
public class CampanhaServiceImpl extends ErroMessage implements CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private CampanhaModelAssembler campanhaModelAssembler;

    @Autowired
    private PagedResourcesAssembler<CampanhaEntity> assembler;

    @Override
    public PagedModel<CampanhaModel> searchCampanha(CampanhaFilter campanhaFilter, Pageable pageable) {
        log.info("CampanhaServiceImpl - getCampanhaByFilter");
        return assembler.toModel(campanhaRepository.findAll(pageable), campanhaModelAssembler);
    }

    @Override
    public CollectionModel<CampanhaModel> getAllCampanhas() {
        log.info("CampanhaServiceImpl - getAllCampanhas");
        return campanhaModelAssembler.toCollectionModel(campanhaRepository.findAll());
    }

    @Override
    public CampanhaModel getCampanhaById(Long id) {
        log.info("CampanhaServiceImpl - getCampanhaById");
        return campanhaRepository.findById(id)
                .map(campanhaModelAssembler::toModel)
                .orElseThrow(() -> notFouldId(id, "a campanha"));
    }

    @Override
    public CampanhaEntity salvar(CampanhaEntity campanha) {
        log.info("CampanhaServiceImpl - save");
        return campanhaRepository.save(campanha);
    }

    @Override
    public CampanhaEntity update(CampanhaEntity campanha) {
        log.info("CampanhaServiceImpl - update put");
        CampanhaEntity campanhaSalvo = campanhaRepository.findById(campanha.getId()).orElseThrow(() -> notFouldId(campanha.getId(), "a campanha"));
        BeanUtils.copyProperties(campanha, campanhaSalvo, "id");
        return campanhaRepository.save(campanhaSalvo);
    }

    @Override
    public CampanhaEntity updatePatch(Long id, Map<Object, Object> fields) {
        log.info("CampanhaServiceImpl - update patch");
        CampanhaEntity campanha = campanhaRepository.findById(id).orElseThrow(() -> notFouldId(id, "campanha"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(CampanhaEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, campanha, value);
        });
        return salvar(campanha);
    }

    @Override
    public CampanhaEntity delete(Long id) {
        log.info("CampanhaServiceImpl - delete");
        CampanhaEntity campanhaSalvo = campanhaRepository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
        campanhaRepository.deleteById(id);
        return campanhaSalvo;
    }
}
