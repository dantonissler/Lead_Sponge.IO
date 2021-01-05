package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;

@Service
public interface EstagioNegociacaoService {
	public EstagioNegociacao save(EstagioNegociacao estagioNegociacao);

	public EstagioNegociacao atualizar(Long id, EstagioNegociacao estagioNegociacao);
}
