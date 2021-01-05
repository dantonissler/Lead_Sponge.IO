package com.leadsponge.IO.repository.segmento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.leadsponge.IO.models.segmento.Segmento;
import com.leadsponge.IO.models.segmento.Segmento_;
import com.leadsponge.IO.repository.Filter.SegmentoFilter;

public class SegmentoRepositoryImpl implements SegmentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Segmento> filtrar(SegmentoFilter segmentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Segmento> criteria = builder.createQuery(Segmento.class);
		Root<Segmento> root = criteria.from(Segmento.class);

		Predicate[] predicates = criarRestricoes(segmentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Segmento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(segmentoFilter));
	}

	private Predicate[] criarRestricoes(SegmentoFilter segmentoFilter, CriteriaBuilder builder, Root<Segmento> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(segmentoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Segmento_.nome)), "%" + segmentoFilter.getNome().toLowerCase() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(SegmentoFilter segmentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Segmento> root = criteria.from(Segmento.class);
		Predicate[] predicates = criarRestricoes(segmentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
