package com.leadsponge.IO.repository.estagioNegociacao;

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

import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao;
import com.leadsponge.IO.models.estagioNegociacao.EstagioNegociacao_;
import com.leadsponge.IO.repository.Filter.EstagioNegociacaoFilter;

public class EstagioNegociacaoRepositoryImpl implements EstagioNegociacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<EstagioNegociacao> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EstagioNegociacao> criteria = builder.createQuery(EstagioNegociacao.class);
		Root<EstagioNegociacao> root = criteria.from(EstagioNegociacao.class);

		Predicate[] predicates = criarRestricoes(estagioNegociacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<EstagioNegociacao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(estagioNegociacaoFilter));
	}

	private Predicate[] criarRestricoes(EstagioNegociacaoFilter estagioNegociacaoFilter, CriteriaBuilder builder, Root<EstagioNegociacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.hasText(estagioNegociacaoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(EstagioNegociacao_.nome)), "%" + estagioNegociacaoFilter.getNome().toLowerCase() + "%"));
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

	private Long total(EstagioNegociacaoFilter estagioNegociacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EstagioNegociacao> root = criteria.from(EstagioNegociacao.class);
		Predicate[] predicates = criarRestricoes(estagioNegociacaoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
