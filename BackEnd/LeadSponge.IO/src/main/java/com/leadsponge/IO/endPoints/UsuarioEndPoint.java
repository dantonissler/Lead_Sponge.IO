package com.leadsponge.IO.endPoints;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leadsponge.IO.dto.Anexo;
import com.leadsponge.IO.endPoints.crudEndpoints.CrudController;
import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.repository.Filter.UsuarioFilter;
import com.leadsponge.IO.repository.projection.ResumoUsuario;
import com.leadsponge.IO.repository.usuario.UsuarioRepository;
import com.leadsponge.IO.services.UsuarioService;
import com.leadsponge.IO.storage.S3;

@RestController
@RequestMapping("/usuarios")
class UsuarioEndPoint extends CrudController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private S3 s3;

	@GetMapping
	@PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public Page<Usuario> pesquisar(UsuarioFilter usuarioFilter, Pageable pageable) {
		return repository.filtrar(usuarioFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public Page<ResumoUsuario> resumir(UsuarioFilter usuarioFilter, Pageable pageable) {
		return repository.resumir(usuarioFilter, pageable);
	}

	@PostMapping(value = { "", "/" })
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvar = usuarioService.save(usuario);
//		usuarioService.autoLogin(usuario.getUsername(), usuario.getPassword());
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvar.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvar);
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> detalhar(@Valid @PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).orElseThrow(() -> notFouldId(id, "o usuario")));
	}

	@PutMapping(value = { "/{id}", "/{id}/" })
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario novoUsuario, @PathVariable Long id,
			HttpServletResponse response) {
		try {
			Usuario usuario = usuarioService.atualizar(id, novoUsuario);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, usuario.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		} catch (IllegalArgumentException e) {
			throw notFouldId(id, "o usuario");
		}
	}

	@DeleteMapping(value = { "/{id}", "/{id}/" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('REMOVER_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> remover(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw notFouldId(id, "o usuario");
		}
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> atualizarPropriedadeEnabled(@PathVariable Long id, @RequestBody Boolean enabled) {
		usuarioService.atualizarPropriedadeEnabled(id, enabled);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/anexo")
	@PreAuthorize("hasAuthority('CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public Anexo uploadAnexo(@RequestParam MultipartFile anexo) throws IOException {
		String nome = s3.salvarTemporariamente(anexo);
		return new Anexo(nome, s3.configurarUrl(nome));
	}
	
	@GetMapping(value = { "/username/{username}", "/username/{username}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> encontrarPeloNome(@Valid @PathVariable String username) {
		return ResponseEntity.ok(repository.findByUsername(username).orElseThrow(() -> notFould(username+"o usuario")));
	}

}
