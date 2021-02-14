package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;

@Service
public interface CampanhaService {
	Campanha salvar(Campanha campanha);

	Campanha atualizar(Long id, Campanha campanha);

	Campanha deletar(Long id);

	Campanha detalhar(Long id);

	Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable);
}
