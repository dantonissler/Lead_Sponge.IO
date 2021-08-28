package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.FonteNegociacaoFilter;

@Service
public interface FonteNegociacaoService {
	FonteNegociacao salvar(FonteNegociacao fonteNegociacao);

	FonteNegociacao atualizar(Long id, FonteNegociacao fonteNegociacao);

	FonteNegociacao deletar(Long id);

	FonteNegociacao detalhar(Long id);

	Page<FonteNegociacao> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable);
}
