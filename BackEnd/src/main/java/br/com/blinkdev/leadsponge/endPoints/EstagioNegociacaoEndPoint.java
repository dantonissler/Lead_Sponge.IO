package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacaoFilter;
import br.com.blinkdev.leadsponge.services.estagioNegocianao.EstagioNegociacaoService;
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
@RequestMapping("/estagios")
class EstagioNegociacaoEndPoint {

    @Autowired
    private final EstagioNegociacaoService estagioNegociacaoService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
    Page<EstagioNegociacao> list(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable) {
        return estagioNegociacaoService.filtrar(estagioNegociacaoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
    ResponseEntity<EstagioNegociacao> cadastrar(@Valid @RequestBody EstagioNegociacao estagioNegociacao, HttpServletResponse response) {
        EstagioNegociacao criarEstagioNegociacao = estagioNegociacaoService.salvar(estagioNegociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarEstagioNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarEstagioNegociacao);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_ESTAGIO') and #oauth2.hasScope('write')")
    ResponseEntity<EstagioNegociacao> atualizar(@Valid @RequestBody EstagioNegociacao estagioNegociacao, @PathVariable Long id, HttpServletResponse response) {
        EstagioNegociacao novaEstagioNegociacao = estagioNegociacaoService.atualizar(id, estagioNegociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaEstagioNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEstagioNegociacao);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_ESTAGIO') and #oauth2.hasScope('write')")
    ResponseEntity<EstagioNegociacao> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(estagioNegociacaoService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_ESTAGIO') and #oauth2.hasScope('read')")
    public ResponseEntity<EstagioNegociacao> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(estagioNegociacaoService.detalhar(id));
    }
}
