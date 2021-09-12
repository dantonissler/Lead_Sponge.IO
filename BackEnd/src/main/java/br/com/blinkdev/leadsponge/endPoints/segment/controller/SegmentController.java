package br.com.blinkdev.leadsponge.endPoints.segment.controller;

import br.com.blinkdev.leadsponge.endPoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endPoints.segment.filter.SegmentFilter;
import br.com.blinkdev.leadsponge.endPoints.segment.service.SegmentService;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "segments", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Segments")
class SegmentController {

    @Autowired
    private final SegmentService segmentService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public Page<SegmentEntity> list(SegmentFilter segmentoFilter, Pageable pageable) {
        return segmentService.filtrar(segmentoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<SegmentEntity> cadastrar(@Valid @RequestBody SegmentEntity segmento, HttpServletResponse response) {
        SegmentEntity criarSegmento = segmentService.salvar(segmento);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarSegmento.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarSegmento);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    ResponseEntity<SegmentEntity> atualizar(@Valid @RequestBody SegmentEntity segmento, @PathVariable Long id, HttpServletResponse response) {
        SegmentEntity novaSegmento = segmentService.atualizar(id, segmento);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaSegmento.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSegmento);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_SEGMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<SegmentEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(segmentService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<SegmentEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(segmentService.detalhar(id));
    }

}
