package br.com.blinkdev.leadsponge.end_points.negotiation_style.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.entity.NegotiationStyleEntity_;
import br.com.blinkdev.leadsponge.end_points.negotiation_style.filter.NegotiationStyleFilter;
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

public class NegotiationStyleRepositoryImpl implements NegotiationStyleRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<NegotiationStyleEntity> filtrar(NegotiationStyleFilter estagioNegociacaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<NegotiationStyleEntity> criteria = builder.createQuery(NegotiationStyleEntity.class);
        Root<NegotiationStyleEntity> root = criteria.from(NegotiationStyleEntity.class);

        Predicate[] predicates = criarRestricoes(estagioNegociacaoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<NegotiationStyleEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(estagioNegociacaoFilter));
    }

    private Predicate[] criarRestricoes(NegotiationStyleFilter estagioNegociacaoFilter, CriteriaBuilder builder, Root<NegotiationStyleEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (estagioNegociacaoFilter.getName() != null && !estagioNegociacaoFilter.getName().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(NegotiationStyleEntity_.name)), "%" + estagioNegociacaoFilter.getName().toLowerCase() + "%"));
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

    private Long total(NegotiationStyleFilter estagioNegociacaoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<NegotiationStyleEntity> root = criteria.from(NegotiationStyleEntity.class);
        Predicate[] predicates = criarRestricoes(estagioNegociacaoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
