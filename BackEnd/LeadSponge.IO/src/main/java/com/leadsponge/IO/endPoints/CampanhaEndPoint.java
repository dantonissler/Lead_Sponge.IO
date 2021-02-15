package com.leadsponge.IO.endPoints;

import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;
import com.leadsponge.IO.services.CampanhaService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/campanhas")
public class CampanhaEndPoint {

    @Autowired
    private final CampanhaService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ApiOperation(value = "Pesquisar campanhas")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public Page<Campanha> pesquisar(CampanhaFilter campanhaFilter, Pageable pageable) {
        return service.filtrar(campanhaFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<Campanha> cadastrar(@Valid @RequestBody Campanha campanha, HttpServletResponse response) {
        Campanha criar = service.salvar(campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criar.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criar);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_CAMPANHA') and #oauth2.hasScope('write')")
    public ResponseEntity<Campanha> atualizar(@Valid @RequestBody Campanha campanha, @PathVariable Long id, HttpServletResponse response) {
        Campanha novaCampanha = service.atualizar(id, campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCampanha.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCampanha);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_CAMPANHA') and #oauth2.hasScope('write')")

    public ResponseEntity<Campanha> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")

    public ResponseEntity<Campanha> detalhar(@Valid @PathVariable("id") Long id, HttpServletResponse response) {
        publisher.publishEvent(new RecursoCriadoEvent(this, response, id));
        return ResponseEntity.ok(service.detalhar(id));
    }
}
