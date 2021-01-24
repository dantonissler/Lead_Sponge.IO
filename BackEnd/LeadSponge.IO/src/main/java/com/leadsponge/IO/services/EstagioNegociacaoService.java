package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;

@Service
public interface EstagioNegociacaoService {
	public EstagioNegociacao salvar(EstagioNegociacao estagioNegociacao);

	public EstagioNegociacao atualizar(Long id, EstagioNegociacao estagioNegociacao);

	public EstagioNegociacao deletar(Long id);

	public EstagioNegociacao detalhar(Long id);

	public Page<EstagioNegociacao> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable);
}
