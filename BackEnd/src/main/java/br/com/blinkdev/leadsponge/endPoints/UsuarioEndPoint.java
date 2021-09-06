package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.models.anexo.AnexoDTO;
import br.com.blinkdev.leadsponge.models.usuario.Usuario;
import br.com.blinkdev.leadsponge.models.usuario.UsuarioFilter;
import br.com.blinkdev.leadsponge.models.usuario.UsuarioModel;
import br.com.blinkdev.leadsponge.models.usuario.UsuarioTO;
import br.com.blinkdev.leadsponge.services.usuario.UsuarioService;
import br.com.blinkdev.leadsponge.storage.Disco;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping(value="/usuarios", produces="application/hal+json")
public class UsuarioEndPoint {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @Autowired
    private final Disco disco;

    /**
     * TODO: utilizar as referencias para implementar HATEOAS
     *
     * https://howtodoinjava.com/spring5/hateoas/spring-hateoas-tutorial/
     * https://howtodoinjava.com/spring5/hateoas/pagination-links/
     */
    @GetMapping
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public Page<UsuarioModel> list(UsuarioFilter usuarioFilter, Pageable pageable) {
        Page<UsuarioModel> retornos = usuarioService.list(usuarioFilter, pageable);
        if (retornos.isEmpty()) {
            return retornos;
        } else {
            for (UsuarioModel retorno : retornos) {
                long id = retorno.getId();
                retorno.add(linkTo(methodOn(UsuarioEndPoint.class).detalhar(id)).withSelfRel());
            }
            return retornos;
        }
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<UsuarioModel> detalhar(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.detalhar(id));
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
//		usuarioService.autoLogin(usuario.getUsername(), usuario.getPassword());
        Usuario usuarioSalvar = usuarioService.salvar(usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvar.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvar);
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable Long id, HttpServletResponse response) {
        Usuario novaUsuario = usuarioService.atualizar(id, usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaUsuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaUsuario);
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('REMOVER_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Usuario> remover(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.deletar(id));
    }

    @PutMapping(value = {"/{id}/ativo"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Usuario> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Boolean enabled) {
        usuarioService.atualizarPropriedadeEnabled(id, enabled);
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
        return ResponseEntity.ok(usuarioService.findByNome(username));
    }

    @PutMapping(value = {"/{id}/foto"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> atualizarImg(@PathVariable Long id, @RequestBody String foto) {
        usuarioService.atualizarImg(id, foto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = {"/{id}/removerFoto"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> removerImg(@PathVariable Long id) {
        usuarioService.removerImg(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = {"/{id}/atualizar"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<Usuario> atualizarUsuarioDTO(@PathVariable Long id, @RequestBody UsuarioTO usuario, HttpServletResponse response) {
        Usuario novoUsuario = usuarioService.atualizarUsuarioDTO(id, usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novoUsuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

}
