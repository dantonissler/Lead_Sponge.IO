package br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.service;

import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.filter.EstagioNegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EstagioNegociacaoService {
	EstagioNegociacaoEntity salvar(EstagioNegociacaoEntity estagioNegociacao);

	EstagioNegociacaoEntity atualizar(Long id, EstagioNegociacaoEntity estagioNegociacao);

	EstagioNegociacaoEntity deletar(Long id);

	EstagioNegociacaoEntity detalhar(Long id);

	Page<EstagioNegociacaoEntity> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable);
}
