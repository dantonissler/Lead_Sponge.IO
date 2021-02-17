package com.leadsponge.IO.endPoints;

import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.repository.Filter.SegmentoFilter;
import com.leadsponge.IO.services.SegmentoService;
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
class SegmentoEndPoint {

    @Autowired
    private final SegmentoService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public Page<Segmento> pesquisar(SegmentoFilter segmentoFilter, Pageable pageable) {
        return service.filtrar(segmentoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Segmento> cadastrar(@Valid @RequestBody Segmento segmento, HttpServletResponse response) {
        Segmento criarSegmento = service.salvar(segmento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarSegmento.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarSegmento);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_SEGMENTO') and #oauth2.hasScope('write')")
    ResponseEntity<Segmento> atualizar(@Valid @RequestBody Segmento segmento, @PathVariable Long id, HttpServletResponse response) {
        Segmento novaSegmento = service.atualizar(id, segmento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaSegmento.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSegmento);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_SEGMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Segmento> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_SEGMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Segmento> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

}
