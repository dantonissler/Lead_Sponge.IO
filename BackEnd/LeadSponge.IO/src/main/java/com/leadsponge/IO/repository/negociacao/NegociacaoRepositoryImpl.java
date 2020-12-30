package com.leadsponge.IO.repository.negociacao;

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

import com.leadsponge.IO.models.negociacao.Negociacao;
import com.leadsponge.IO.models.negociacao.Negociacao_;
import com.leadsponge.IO.repository.Filter.NegociacaoFilter;

public class NegociacaoRepositoryImpl implements NegociacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Negociacao> filtrar(NegociacaoFilter negociacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Negociacao> criteria = builder.createQuery(Negociacao.class);
		Root<Negociacao> root = criteria.from(Negociacao.class);

		Predicate[] predicates = criarRestricoes(negociacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Negociacao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(negociacaoFilter));
	}

	private Predicate[] criarRestricoes(NegociacaoFilter negociacaoFilter, CriteriaBuilder builder, Root<Negociacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.hasText(negociacaoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Negociacao_.nome)), "%" + negociacaoFilter.getNome().toLowerCase() + "%"));
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

	private Long total(NegociacaoFilter negociacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Negociacao> root = criteria.from(Negociacao.class);
		Predicate[] predicates = criarRestricoes(negociacaoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
