package br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.service;

import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.filter.FonteNegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface FonteNegociacaoService {
	FonteNegociacaoEntity salvar(FonteNegociacaoEntity fonteNegociacao);

	FonteNegociacaoEntity atualizar(Long id, FonteNegociacaoEntity fonteNegociacao);

	FonteNegociacaoEntity deletar(Long id);

	FonteNegociacaoEntity detalhar(Long id);

	Page<FonteNegociacaoEntity> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable);
}
