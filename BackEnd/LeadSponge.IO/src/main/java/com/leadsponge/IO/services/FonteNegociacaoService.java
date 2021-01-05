package com.leadsponge.IO.services;

import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;

@Service
public interface FonteNegociacaoService {
	public FonteNegociacao save(FonteNegociacao fonteNegociacao);

	public FonteNegociacao atualizar(Long id, FonteNegociacao fonteNegociacao);
}
