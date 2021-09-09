package br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.filter.EstagioNegociacaoFilter;
import br.com.blinkdev.leadsponge.endPoints.estagioNegociacao.entity.EstagioNegociacaoEntity_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EstagioNegociacaoRepositoryImpl implements EstagioNegociacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<EstagioNegociacaoEntity> filtrar(EstagioNegociacaoFilter estagioNegociacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EstagioNegociacaoEntity> criteria = builder.createQuery(EstagioNegociacaoEntity.class);
		Root<EstagioNegociacaoEntity> root = criteria.from(EstagioNegociacaoEntity.class);

		Predicate[] predicates = criarRestricoes(estagioNegociacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<EstagioNegociacaoEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(estagioNegociacaoFilter));
	}

	private Predicate[] criarRestricoes(EstagioNegociacaoFilter estagioNegociacaoFilter, CriteriaBuilder builder, Root<EstagioNegociacaoEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (estagioNegociacaoFilter.getNome().isBlank()) {
			predicates.add(builder.like(builder.lower(root.get(EstagioNegociacaoEntity_.nome)), "%" + estagioNegociacaoFilter.getNome().toLowerCase() + "%"));
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
		Root<EstagioNegociacaoEntity> root = criteria.from(EstagioNegociacaoEntity.class);
		Predicate[] predicates = criarRestricoes(estagioNegociacaoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
