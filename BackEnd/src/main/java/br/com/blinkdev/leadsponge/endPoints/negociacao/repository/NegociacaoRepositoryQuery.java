package br.com.blinkdev.leadsponge.endPoints.negociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.negociacao.entity.NegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacao.filter.NegociacaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NegociacaoRepositoryQuery {
	Page<NegociacaoEntity> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable);
}
