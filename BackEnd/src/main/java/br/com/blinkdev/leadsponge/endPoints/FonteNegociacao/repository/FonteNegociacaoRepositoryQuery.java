package br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.filter.FonteNegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FonteNegociacaoRepositoryQuery {
	Page<FonteNegociacaoEntity> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable);

}
