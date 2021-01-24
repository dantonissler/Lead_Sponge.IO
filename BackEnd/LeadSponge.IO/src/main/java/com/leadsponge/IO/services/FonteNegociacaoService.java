package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.repository.Filter.FonteNegociacaoFilter;

@Service
public interface FonteNegociacaoService {
	public FonteNegociacao salvar(FonteNegociacao fonteNegociacao);

	public FonteNegociacao atualizar(Long id, FonteNegociacao fonteNegociacao);

	public FonteNegociacao deletar(Long id);

	public FonteNegociacao detalhar(Long id);

	public Page<FonteNegociacao> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable);
}
