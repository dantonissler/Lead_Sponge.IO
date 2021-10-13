package br.com.blinkdev.leadsponge.end_points.segment.controller;

import br.com.blinkdev.leadsponge.end_points.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.end_points.segment.filter.SegmentFilter;
import br.com.blinkdev.leadsponge.end_points.segment.model.SegmentModel;
import br.com.blinkdev.leadsponge.end_points.segment.service.SegmentService;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
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
@RequestMapping(value = "segments", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Segments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class SegmentController {
    private final SegmentService segmentService;
    private final ApplicationEventPublisher publisher;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get segment by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public SegmentModel getById(@Valid @PathVariable("id") Long id) {
        return segmentService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search segments with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public PagedModel<SegmentModel> searchWithFilters(SegmentFilter segmentoFilter, Pageable pageable) {
        return segmentService.searchWithFilters(segmentoFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save segment.")
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    public SegmentModel save(@Valid @RequestBody SegmentEntity segmento, HttpServletResponse response) {
        SegmentModel criarSegmento = segmentService.save(segmento);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, criarSegmento.getId()));
        return criarSegmento;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch segment.")
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    public SegmentModel patch(@Valid @RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        SegmentModel novaSegmento = segmentService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novaSegmento.getId()));
        return novaSegmento;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete segment.")
    @PreAuthorize("hasAuthority('REMOVER_SEGMENTO') and #oauth2.hasScope('write')")
    public SegmentModel delete(@PathVariable Long id) {
        return segmentService.delete(id);
    }

}
