package br.com.blinkdev.leadsponge.endPoints.user.controller;

import br.com.blinkdev.leadsponge.endPoints.user.TO.AnexoTO;
import br.com.blinkdev.leadsponge.endPoints.user.TO.UsuarioTO;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
import br.com.blinkdev.leadsponge.endPoints.user.service.UserService;
import br.com.blinkdev.leadsponge.event.RecursoCriadoEvent;
import br.com.blinkdev.leadsponge.storage.Disco;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/usuarios", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "User")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ApplicationEventPublisher publisher;

    @Autowired
    private final Disco disco;

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<UserModel> getById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping(value = {"/getAll"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<CollectionModel<UserModel>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/searchWithFilter"})
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public ResponseEntity<PagedModel<UserModel>> searchWithFilter(UserFilter userFilter, Pageable pageable) {
        return new ResponseEntity<>(userService.searchWithFilter(userFilter, pageable), HttpStatus.OK);
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<UserEntity> save(@Valid @RequestBody UserEntity usuario, HttpServletResponse response) {
//		usuarioService.autoLogin(usuario.getUsername(), usuario.getPassword());
        UserEntity usuarioSalvar = userService.save(usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvar.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvar);
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<UserEntity> update(@Valid @RequestBody UserEntity usuario, @PathVariable Long id, HttpServletResponse response) {
        UserEntity novaUsuario = userService.update(id, usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaUsuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaUsuario);
    }

    @PatchMapping(value = {"/patch/{id}"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<UserEntity> updatePatch(@RequestBody Map<Object, Object> campanha, @PathVariable Long id, HttpServletResponse response) {
        UserEntity novaCampanha = userService.updatePatch(id, campanha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCampanha.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(novaCampanha);
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('REMOVER_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<UserEntity> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @PutMapping(value = {"/{id}/ativo"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<UserEntity> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Boolean enabled) {
        userService.atualizarPropriedadeEnabled(id, enabled);
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
    AnexoTO uploadFoto(@RequestParam MultipartFile foto) {
        String nome = disco.salvarFoto(foto);
        return new AnexoTO(nome, disco.configurarUrlFoto(nome));
    }

    @GetMapping(value = {"/username/{username}"})
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    ResponseEntity<UserEntity> encontrarPeloNome(@Valid @PathVariable String username) {
        return ResponseEntity.ok(userService.findByNome(username));
    }

    @PutMapping(value = {"/{id}/foto"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<UserEntity> atualizarImg(@PathVariable Long id, @RequestBody String foto) {
        userService.atualizarImg(id, foto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = {"/{id}/removerFoto"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<UserEntity> removerImg(@PathVariable Long id) {
        userService.removerImg(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = {"/{id}/atualizar"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CADASTRAR_NEGOCIACAO') and #oauth2.hasScope('write')")
    ResponseEntity<UserEntity> atualizarUsuarioDTO(@PathVariable Long id, @RequestBody UsuarioTO usuario, HttpServletResponse response) {
        UserEntity novoUsuario = userService.atualizarUsuarioDTO(id, usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novoUsuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

}
