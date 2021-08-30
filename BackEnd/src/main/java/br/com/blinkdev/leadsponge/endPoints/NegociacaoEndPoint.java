package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.negociacao.EstatusNegociacao;
import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import br.com.blinkdev.leadsponge.models.negociacao.NegociacaoFilter;
import br.com.blinkdev.leadsponge.services.NegociacaoService;
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
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/negociacoes")
class NegociacaoEndPoint {

    @Autowired
    private final NegociacaoService negociacaoService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
    Page<Negociacao> entryPoint(NegociacaoFilter negociacaoFilter, Pageable pageable) {
        return negociacaoService.filtrar(negociacaoFilter, pageable);
    }

    @PostMapping(value = {""})
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> cadastrar(@Valid @RequestBody Negociacao negociacao, HttpServletResponse response) {
        Negociacao criarNegociacao = negociacaoService.salvar(negociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, criarNegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(criarNegociacao);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_NEGOCIACAO') and #oauth2.hasScope('read')")
    ResponseEntity<Negociacao> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(negociacaoService.detalhar(id));
    }

    @PutMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> atualizar(@RequestBody Negociacao negociacao, @PathVariable Long id, HttpServletResponse response) {
        Negociacao novonegociacao = negociacaoService.atualizar(id, negociacao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novonegociacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novonegociacao);
    }

    @DeleteMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('REMOVER_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(negociacaoService.deletar(id));
    }

    @PutMapping("/{id}/avaliacao")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Integer avaliacao) {
        negociacaoService.atualizarPropriedadeAvaliacao(id, avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/estagio")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> atualizarPropriedadeEstagio(@PathVariable Long id, @RequestBody EstagioNegociacao estagio) {
        negociacaoService.atualizarPropriedadeEstagio(id, estagio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/estatus")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> atualizarPropriedadeEstatus(@PathVariable Long id, @RequestBody EstatusNegociacao estatus) {
        negociacaoService.atualizarPropriedadeEstatus(id, estatus);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/dataFim")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> atualizarPropriedadeDataFim(@PathVariable Long id, @RequestBody LocalDateTime data) {
        negociacaoService.atualizarPropriedadeDataFim(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/perda")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Negociacao> atribuirMotivoPerda(@PathVariable Long id, @RequestBody MotivoPerda morivoPerda) {
        negociacaoService.atribuirPropMP(id, morivoPerda);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
