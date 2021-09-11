package br.com.blinkdev.leadsponge.endPoints.campanha.controller;

import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampaignFilters;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampaignModel;
import br.com.blinkdev.leadsponge.endPoints.campanha.service.CampaignService;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "campaigns", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(value = {"{id}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get campaign by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<CampaignModel> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(campaignService.getById(id));
    }

    @GetMapping(value = {"/searchWithFilter"})
    @ApiOperation(value = "Search campaigns with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<PagedModel<CampaignModel>> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable) {
        return new ResponseEntity<>(campaignService.searchWithFilters(campanhaFilter, pageable), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Save campaign.")
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<CampaignModel> save(@Valid @RequestBody CampaignEntity campanha, HttpServletResponse response) {
        CampaignModel criar = campaignService.save(campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criar.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criar);
    }

    @PatchMapping(value = {"{id}"})
    @ApiOperation(value = "Patch campaign.")
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<CampaignModel> patch(@RequestBody Map<Object, Object> campanha, @PathVariable Long id, HttpServletResponse response) {
        CampaignModel novaCampanha = campaignService.patch(id, campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCampanha.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(novaCampanha);
    }

    @DeleteMapping(value = {"{id}"})
    @ApiOperation(value = "Delete campaign.")
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<CampaignModel> delete(@PathVariable Long id) {
        return ResponseEntity.ok(campaignService.delete(id));
    }
}
