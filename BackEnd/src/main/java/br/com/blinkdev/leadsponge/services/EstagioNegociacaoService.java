package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.EstagioNegociacaoFilter;

@Service
public interface EstagioNegociacaoService {
	EstagioNegociacao salvar(EstagioNegociacao estagioNegociacao);

	EstagioNegociacao atualizar(Long id, EstagioNegociacao estagioNegociacao);

	EstagioNegociacao deletar(Long id);

	EstagioNegociacao detalhar(Long id);

	Page<EstagioNegociacao> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable);
}
