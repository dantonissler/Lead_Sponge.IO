package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;

@Service
public interface EstagioNegociacaoService {
	EstagioNegociacao salvar(EstagioNegociacao estagioNegociacao);

	EstagioNegociacao atualizar(Long id, EstagioNegociacao estagioNegociacao);

	EstagioNegociacao deletar(Long id);

	EstagioNegociacao detalhar(Long id);

	Page<EstagioNegociacao> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable);
}
