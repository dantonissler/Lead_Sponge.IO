package br.com.blinkdev.leadsponge.endpoints.campaign.service;

import br.com.blinkdev.leadsponge.endpoints.campaign.Filter.CampaignFilters;
import br.com.blinkdev.leadsponge.endpoints.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endpoints.campaign.event.CampaignCreatedEvent;
import br.com.blinkdev.leadsponge.endpoints.campaign.model.CampaignModel;
import br.com.blinkdev.leadsponge.endpoints.campaign.modelAssembler.CampaignModelAssembler;
import br.com.blinkdev.leadsponge.endpoints.campaign.repository.CampaignRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignServiceImpl extends ErroMessage implements CampaignService {
    private final CampaignRepository campanhaRepository;
    private final CampaignModelAssembler campanhaModelAssembler;
    private final PagedResourcesAssembler<CampaignEntity> assembler;

    @Override
    public CampaignModel getById(Long id) {
        log.info("CampanhaService - getById");
        return campanhaRepository.findById(id).map(campanhaModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[campaign]"));
    }

    @Override
    public PagedModel<CampaignModel> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable) {
        log.info("CampanhaService - searchWithFilters");
        return assembler.toModel(campanhaRepository.searchWithFilters(campanhaFilter, pageable), campanhaModelAssembler);
    }

    @Override
    @Transactional
    public CampaignModel save(CampaignEntity campanha) {
        log.info("CampanhaService - save");
        return campanhaModelAssembler.toModel(campanhaRepository.save(campanha));
    }

    @Override
    @Transactional
    public CampaignModel patch(Long id, Map<Object, Object> fields) {
        log.info("CampanhaService - patch");
        CampaignEntity campaignEntity = campanhaRepository.findById(id).orElseThrow(() -> notFouldId(id, "[campaign]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(CampaignEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, campaignEntity, value);
        });
        return save(campaignEntity);
    }

    @Override
    @Transactional
    public CampaignModel delete(Long id) {
        log.info("CampanhaService - delete");
        CampaignEntity campaignEntity = campanhaRepository.findById(id).orElseThrow(() -> notFouldId(id, "[campaign]"));
        campanhaRepository.deleteById(id);
        return campanhaModelAssembler.toModel(campaignEntity);
    }

    @EventListener()
    public void verificacao(CampaignCreatedEvent campaignCreatedEvent) {
        log.info("Testando EventListener.");
    }
}
