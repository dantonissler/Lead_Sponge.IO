package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.services.FonteNegociacaoService;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.repository.Filter.FonteNegociacaoFilter;
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
@RequestMapping("/fontes")
class FonteNegociacaoEndPoint {

    @Autowired
    private final FonteNegociacaoService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public Page<FonteNegociacao> pesquisar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
        return service.filtrar(fonteNegociacaoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    public ResponseEntity<FonteNegociacao> cadastrar(@Valid @RequestBody FonteNegociacao fonteNegociacao, HttpServletResponse response) {
        FonteNegociacao fonteNegociacaoNegociacao = service.salvar(fonteNegociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, fonteNegociacaoNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    ResponseEntity<FonteNegociacao> atualizar(@Valid @RequestBody FonteNegociacao fonteNegociacao, @PathVariable Long id, HttpServletResponse response) {
        FonteNegociacao fonteNegociacaoNegociacao = service.atualizar(id, fonteNegociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, fonteNegociacaoNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_FONTE') and #oauth2.hasScope('write')")
    public ResponseEntity<FonteNegociacao> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public ResponseEntity<FonteNegociacao> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }
}
