package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.telefone.Telefone;
import com.leadsponge.IO.repository.Filter.TelefoneFilter;

@Service
public interface TelefoneService {
	public Telefone salvar(Telefone contato);

	public Telefone atualizar(Long id, Telefone contato);

	public Telefone deletar(Long id);

	public Telefone detalhar(Long id);

	public Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable);

}
