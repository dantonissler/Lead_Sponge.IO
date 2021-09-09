package br.com.blinkdev.leadsponge.endPoints.campanha.controller;

import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampanhaFilter;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampanhaEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.model.CampanhaModel;
import br.com.blinkdev.leadsponge.endPoints.campanha.service.CampanhaService;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
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
@RequestMapping(value = "campanhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(value = {"/filter"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<PagedModel<CampanhaModel>> searchCampanha(CampanhaFilter campanhaFilter, Pageable pageable) {
        return new ResponseEntity<>(campanhaService.searchCampanha(campanhaFilter, pageable), HttpStatus.OK);
    }

    @GetMapping(value = {"/list"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<CollectionModel<CampanhaModel>> getAllCampanhas() {
        return new ResponseEntity<>(campanhaService.getAllCampanhas(), HttpStatus.OK);
    }

    @GetMapping(value = {"{id}"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<CampanhaModel> getCampanhaById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(campanhaService.getCampanhaById(id));
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<CampanhaEntity> save(@Valid @RequestBody CampanhaEntity campanha, HttpServletResponse response) {
        CampanhaEntity criar = campanhaService.salvar(campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criar.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criar);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<CampanhaEntity> update(@Valid @RequestBody CampanhaEntity campanha, HttpServletResponse response) {
        CampanhaEntity novaCampanha = campanhaService.update(campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCampanha.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(novaCampanha);
    }

    @PatchMapping(value = {"{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<CampanhaEntity> updatePatch(@RequestBody Map<Object, Object> campanha, @PathVariable Long id, HttpServletResponse response) {
        CampanhaEntity novaCampanha = campanhaService.updatePatch(id, campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCampanha.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(novaCampanha);
    }

    @DeleteMapping(value = {"{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<CampanhaEntity> delete(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.delete(id));
    }
}
