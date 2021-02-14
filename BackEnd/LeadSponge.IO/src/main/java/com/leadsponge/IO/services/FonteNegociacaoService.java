package com.leadsponge.IO.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.repository.Filter.FonteNegociacaoFilter;

@Service
public interface FonteNegociacaoService {
	FonteNegociacao salvar(FonteNegociacao fonteNegociacao);

	FonteNegociacao atualizar(Long id, FonteNegociacao fonteNegociacao);

	FonteNegociacao deletar(Long id);

	FonteNegociacao detalhar(Long id);

	Page<FonteNegociacao> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable);
}
