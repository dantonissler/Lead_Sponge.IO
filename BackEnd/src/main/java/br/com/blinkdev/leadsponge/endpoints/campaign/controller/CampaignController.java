package br.com.blinkdev.leadsponge.endpoints.campaign.controller;

import br.com.blinkdev.leadsponge.endpoints.campaign.Filter.CampaignFilters;
import br.com.blinkdev.leadsponge.endpoints.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endpoints.campaign.event.CampaignCreatedEvent;
import br.com.blinkdev.leadsponge.endpoints.campaign.event.CampaignPatchEvent;
import br.com.blinkdev.leadsponge.endpoints.campaign.model.CampaignModel;
import br.com.blinkdev.leadsponge.endpoints.campaign.service.CampaignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "campaigns", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Campaigns")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CampaignController {
    private final CampaignService campaignService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"{id}"})
    @ApiOperation(value = "Get campaign by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public CampaignModel getById(@PathVariable("id") Long id) {
        return campaignService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search campaigns with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public PagedModel<CampaignModel> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable) {
        return campaignService.searchWithFilters(campanhaFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save campaign.")
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public CampaignModel save(@Valid @RequestBody CampaignEntity campanha, HttpServletResponse response) {
        CampaignModel campaignModel = campaignService.save(campanha);
        publisher.publishEvent(new CampaignCreatedEvent(this, response, campaignModel.getId()));
        return campaignModel;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"{id}"})
    @ApiOperation(value = "Patch campaign.")
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public CampaignModel patch(@RequestBody Map<Object, Object> campanha, @PathVariable Long id, HttpServletResponse response) {
        CampaignModel campaignModel = campaignService.patch(id, campanha);
        publisher.publishEvent(new CampaignPatchEvent(this, response, campaignModel.getId()));
        return campaignModel;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"{id}"})
    @ApiOperation(value = "Delete campaign.")
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
    public CampaignModel delete(@PathVariable Long id) {
        return campaignService.delete(id);
    }
}
