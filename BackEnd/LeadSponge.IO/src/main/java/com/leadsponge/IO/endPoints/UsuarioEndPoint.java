package com.leadsponge.IO.endPoints;

import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.anexo.AnexoDTO;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.models.usuario.UsuarioTO;
import com.leadsponge.IO.repository.Filter.UsuarioFilter;
import com.leadsponge.IO.repository.projection.UsuarioResumo;
import com.leadsponge.IO.services.UsuarioService;
import com.leadsponge.IO.storage.Disco;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
class UsuarioEndPoint {

    @Autowired
    private final UsuarioService service;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @Autowired
    private final Disco disco;

    @GetMapping
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public Page<Usuario> pesquisar(UsuarioFilter usuarioFilter, Pageable pageable) {
        return service.filtrar(usuarioFilter, pageable);
    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public Page<UsuarioResumo> resumir(UsuarioFilter usuarioFilter, Pageable pageable) {
        return service.resumir(usuarioFilter, pageable);
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
//		usuarioService.autoLogin(usuario.getUsername(), usuario.getPassword());
        Usuario usuarioSalvar = service.salvar(usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvar.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvar);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<Usuario> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable Long id, HttpServletResponse response) {
        Usuario novaUsuario = service.atualizar(id, usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaUsuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaUsuario);
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('REMOVER_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Usuario> remover(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    @PutMapping(value = {"/{id}/ativo"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Usuario> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Boolean enabled) {
        service.atualizarPropriedadeEnabled(id, enabled);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//	@PostMapping("/anexo")
//	@PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
//	public Anexo uploadAnexo(@RequestParam MultipartFile anexo) throws IOException {
//		String nome = s3.salvarTemporariamente(anexo);
//		return new Anexo(nome, s3.configurarUrl(nome));
//	}

    @PostMapping("/foto")
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    AnexoDTO uploadFoto(@RequestParam MultipartFile foto) {
        String nome = disco.salvarFoto(foto);
        return new AnexoDTO(nome, disco.configurarUrlFoto(nome));
    }

    @GetMapping(value = {"/username/{username}"})
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    ResponseEntity<Usuario> encontrarPeloNome(@Valid @PathVariable String username) {
        return ResponseEntity.ok(service.findByNome(username));
    }

    @PutMapping(value = {"/{id}/foto"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> atualizarImg(@PathVariable Long id, @RequestBody String foto) {
        service.atualizarImg(id, foto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = {"/{id}/removerFoto"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> removerImg(@PathVariable Long id) {
        service.removerImg(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = {"/{id}/atualizar"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> atualizarUsuarioDTO(@PathVariable Long id, @RequestBody UsuarioTO usuario, HttpServletResponse response) {
        Usuario novoUsuario = service.atualizarUsuarioDTO(id, usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novoUsuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

}
