package br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.repository;

import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.entity.FonteNegociacaoEntity_;
import br.com.blinkdev.leadsponge.endPoints.FonteNegociacao.filter.FonteNegociacaoFilter;
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

public class FonteNegociacaoRepositoryImpl implements FonteNegociacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<FonteNegociacaoEntity> filtrar(FonteNegociacaoFilter fonteNegociacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<FonteNegociacaoEntity> criteria = builder.createQuery(FonteNegociacaoEntity.class);
		Root<FonteNegociacaoEntity> root = criteria.from(FonteNegociacaoEntity.class);
		Predicate[] predicates = criarRestricoes(fonteNegociacaoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<FonteNegociacaoEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(fonteNegociacaoFilter));
	}

	private Predicate[] criarRestricoes(FonteNegociacaoFilter fonteNegociacaoFilter, CriteriaBuilder builder, Root<FonteNegociacaoEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (fonteNegociacaoFilter.getNome() != null && !fonteNegociacaoFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(FonteNegociacaoEntity_.nome)), "%" + fonteNegociacaoFilter.getNome().toLowerCase() + "%"));
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
		Root<FonteNegociacaoEntity> root = criteria.from(FonteNegociacaoEntity.class);
		Predicate[] predicates = criarRestricoes(fonteNegociacaoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
