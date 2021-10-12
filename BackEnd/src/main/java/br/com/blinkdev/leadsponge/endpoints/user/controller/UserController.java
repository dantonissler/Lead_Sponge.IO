package br.com.blinkdev.leadsponge.endpoints.user.controller;

import br.com.blinkdev.leadsponge.endpoints.user.TO.AnexoTO;
import br.com.blinkdev.leadsponge.endpoints.user.TO.UsuarioTO;
import br.com.blinkdev.leadsponge.endpoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endpoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endpoints.user.model.UserModel;
import br.com.blinkdev.leadsponge.endpoints.user.service.UserService;
import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import br.com.blinkdev.leadsponge.storage.Disco;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final Disco disco;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{id}"})
    @ApiOperation(value = "Get custumer by ID.")
    @PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public UserModel getById(@Valid @PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation(value = "Search custumers with a filters.")
    @PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
    public PagedModel<UserModel> searchWithFilter(UserFilter userFilter, Pageable pageable) {
        return userService.searchWithFilter(userFilter, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Save custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public UserModel save(@Valid @RequestBody UserEntity usuario, HttpServletResponse response) {
//		usuarioService.autoLogin(usuario.getUsername(), usuario.getPassword());
        UserModel userEntity = userService.save(usuario);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, userEntity.getId()));
        return userEntity;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = {"/{id}"})
    @ApiOperation(value = "Patch custumer.")
    @PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public UserModel patch(@RequestBody Map<Object, Object> fields, @PathVariable Long id, HttpServletResponse response) {
        UserModel userEntity = userService.patch(id, fields);
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, userEntity.getId()));
        return userEntity;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"})
    @ApiOperation(value = "Delete custumer.")
    @PreAuthorize("hasAuthority('REMOVER_USUARIO') and #oauth2.hasScope('write')")
    public UserModel delete(@PathVariable Long id) {
        return userService.delete(id);
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
        publisher.publishEvent(new ResourcesCreatedEvent(this, response, novoUsuario.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

}
