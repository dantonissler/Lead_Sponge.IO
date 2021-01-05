package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.campanha.Campanha;
import com.leadsponge.IO.repository.Filter.CampanhaFilter;

@Service
public interface CampanhaService {
	public Campanha salvar(Campanha campanha);
	public Campanha atualizar(Long id, Campanha campanha);
	public Boolean deletar(Long id);
	public Campanha detalhar(Long id);
	public Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable);
}
