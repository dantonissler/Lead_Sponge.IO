package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.telefone.Telefone;
import com.leadsponge.IO.repository.Filter.TelefoneFilter;

@Service
public interface TelefoneService {
	Telefone salvar(Telefone contato);

	Telefone atualizar(Long id, Telefone contato);

	Telefone deletar(Long id);

	Telefone detalhar(Long id);

	Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable);

}
