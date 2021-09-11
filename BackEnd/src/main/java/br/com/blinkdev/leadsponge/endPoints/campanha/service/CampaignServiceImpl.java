package br.com.blinkdev.leadsponge.endPoints.campanha.service;

import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampaignFilters;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampaignModel;
import br.com.blinkdev.leadsponge.endPoints.campanha.modelAssembler.CampaignModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.campanha.repository.CampaignRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
public class CampaignServiceImpl extends ErroMessage implements CampaignService {

    @Autowired
    private CampaignRepository campanhaRepository;

    @Autowired
    private CampaignModelAssembler campanhaModelAssembler;

    @Autowired
    private PagedResourcesAssembler<CampaignEntity> assembler;

    @Override
    public CampaignModel getById(Long id) {
        log.info("CampanhaServiceImpl - getById");
        return campanhaRepository.findById(id).map(campanhaModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "a campanha"));
    }

    @Override
    public PagedModel<CampaignModel> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable) {
        log.info("CampanhaServiceImpl - search");
        return assembler.toModel(campanhaRepository.searchWithFilters(campanhaFilter, pageable), campanhaModelAssembler);
    }

    @Override
    public CampaignModel save(CampaignEntity campanha) {
        log.info("CampanhaServiceImpl - save");
        return campanhaModelAssembler.toModel(campanhaRepository.save(campanha));
    }

    @Override
    public CampaignModel patch(Long id, Map<Object, Object> fields) {
        log.info("CampanhaServiceImpl - update patch");
        CampaignEntity campanha = campanhaRepository.findById(id).orElseThrow(() -> notFouldId(id, "campanha"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(CampaignEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, campanha, value);
        });
        return save(campanha);
    }

    @Override
    public CampaignModel delete(Long id) {
        log.info("CampanhaServiceImpl - delete");
        CampaignEntity campanhaSalvo = campanhaRepository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
        campanhaRepository.deleteById(id);
        return campanhaModelAssembler.toModel(campanhaSalvo);
    }
}
