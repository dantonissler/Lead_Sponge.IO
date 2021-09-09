package br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.filter.EstagioNegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioNegociacaoRepositoryQuery {
	Page<EstagioNegociacaoEntity> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable);
}
