package com.leadsponge.IO.services.implementated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.role.Role;
import com.leadsponge.IO.repository.Filter.RoleFilter;
import com.leadsponge.IO.repository.role.RoleRepository;
import com.leadsponge.IO.services.RoleService;

@Service
public class RoleServiceImpl extends ErroMessage implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Override
	public Role detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
	}

	@Override
	public Page<Role> filtrar(RoleFilter roleFilter, Pageable pageable) {
		return repository.filtrar(roleFilter, pageable);
	}

}
