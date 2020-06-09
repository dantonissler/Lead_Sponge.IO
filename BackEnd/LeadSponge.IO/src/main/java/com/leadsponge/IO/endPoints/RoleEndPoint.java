package com.leadsponge.IO.endPoints;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.RoleRepository;

@RestController
@RequestMapping("/roles")
class RoleEndPoint {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public RoleEndPoint(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@GetMapping(value = { "", "/" })
	@PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Iterable<?>> listar() {
		Iterable<Role> role = roleRepository.findAll();
		if (role == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(role);
		}
	}
	
	@GetMapping(value = { "/{id}", "/{id}/" })
	@PreAuthorize("hasAuthority('PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Role> buscarPeloId(@PathVariable Long id) {
		 Optional<Role> role = roleRepository.findById(id);
		 return role.isPresent() ? ResponseEntity.ok(role.get()) : ResponseEntity.notFound().build();
	}
}
