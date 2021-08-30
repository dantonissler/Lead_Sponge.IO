package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacaoFilter;
import br.com.blinkdev.leadsponge.services.FonteNegociacaoService;
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
    private final FonteNegociacaoService fonteNegociacaoService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public Page<FonteNegociacao> entryPoint(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
        return fonteNegociacaoService.filtrar(fonteNegociacaoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    public ResponseEntity<FonteNegociacao> cadastrar(@Valid @RequestBody FonteNegociacao fonteNegociacao, HttpServletResponse response) {
        FonteNegociacao fonteNegociacaoNegociacao = fonteNegociacaoService.salvar(fonteNegociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, fonteNegociacaoNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_FONTE') and #oauth2.hasScope('write')")
    ResponseEntity<FonteNegociacao> atualizar(@Valid @RequestBody FonteNegociacao fonteNegociacao, @PathVariable Long id, HttpServletResponse response) {
        FonteNegociacao fonteNegociacaoNegociacao = fonteNegociacaoService.atualizar(id, fonteNegociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, fonteNegociacaoNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fonteNegociacaoNegociacao);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_FONTE') and #oauth2.hasScope('write')")
    public ResponseEntity<FonteNegociacao> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(fonteNegociacaoService.deletar(id));
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_FONTE') and #oauth2.hasScope('read')")
    public ResponseEntity<FonteNegociacao> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(fonteNegociacaoService.detalhar(id));
    }
}
