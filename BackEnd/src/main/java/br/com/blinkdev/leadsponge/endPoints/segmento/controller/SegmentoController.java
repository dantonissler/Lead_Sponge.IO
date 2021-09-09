package br.com.blinkdev.leadsponge.endPoints.segmento.controller;

import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import br.com.blinkdev.leadsponge.endPoints.segmento.filter.SegmentoFilter;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.endPoints.segmento.service.SegmentoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("segmentos")
class SegmentoController {

    @Autowired
    private final SegmentoService segmentoService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public Page<SegmentoEntity> list(SegmentoFilter segmentoFilter, Pageable pageable) {
        return segmentoService.filtrar(segmentoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<SegmentoEntity> cadastrar(@Valid @RequestBody SegmentoEntity segmento, HttpServletResponse response) {
        SegmentoEntity criarSegmento = segmentoService.salvar(segmento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarSegmento.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarSegmento);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    ResponseEntity<SegmentoEntity> atualizar(@Valid @RequestBody SegmentoEntity segmento, @PathVariable Long id, HttpServletResponse response) {
        SegmentoEntity novaSegmento = segmentoService.atualizar(id, segmento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaSegmento.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSegmento);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_SEGMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<SegmentoEntity> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(segmentoService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<SegmentoEntity> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(segmentoService.detalhar(id));
    }

}
