package com.leadsponge.IO.repository.fonteNegociacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao;
import com.leadsponge.IO.models.fonteNegociacao.FonteNegociacao_;
import com.leadsponge.IO.repository.Filter.FonteNegociacaoFilter;

public class FonteNegociacaoRepositoryImpl implements FonteNegociacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<FonteNegociacao> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<FonteNegociacao> criteria = builder.createQuery(FonteNegociacao.class);
		Root<FonteNegociacao> root = criteria.from(FonteNegociacao.class);

		Predicate[] predicates = criarRestricoes(fonteNegociacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<FonteNegociacao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(fonteNegociacaoFilter));
	}

	private Predicate[] criarRestricoes(FonteNegociacaoFilter fonteNegociacaoFilter, CriteriaBuilder builder, Root<FonteNegociacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.hasText(fonteNegociacaoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(FonteNegociacao_.nome)), "%" + fonteNegociacaoFilter.getNome().toLowerCase() + "%"));
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

	private Long total(FonteNegociacaoFilter fonteNegociacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<FonteNegociacao> root = criteria.from(FonteNegociacao.class);
		Predicate[] predicates = criarRestricoes(fonteNegociacaoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
