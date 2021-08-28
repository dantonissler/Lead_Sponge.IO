package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.campanha.Campanha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.CampanhaFilter;

@Service
public interface CampanhaService {
	Campanha salvar(Campanha campanha);

	Campanha atualizar(Long id, Campanha campanha);

	Campanha deletar(Long id);

	Campanha detalhar(Long id);

	Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable);
}
