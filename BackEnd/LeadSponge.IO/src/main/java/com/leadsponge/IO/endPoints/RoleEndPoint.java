package com.leadsponge.IO.endPoints;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.event.RecursoCriadoEvent;
import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.Filter.RoleFilter;
import com.leadsponge.IO.services.RoleService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
class RoleEndPoint {

	@Autowired
	private RoleService service;

	@Autowired
	private final ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public Page<Role> pesquisar(RoleFilter usuarioFilter, Pageable pageable) {
		return service.filtrar(usuarioFilter, pageable);
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_CAMPANHA') and #oauth2.hasScope('read')")
	ResponseEntity<Role> detalhar(@Valid @PathVariable("id") Long id, HttpServletResponse response) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, id));
		return ResponseEntity.ok(service.detalhar(id));
	}
}
