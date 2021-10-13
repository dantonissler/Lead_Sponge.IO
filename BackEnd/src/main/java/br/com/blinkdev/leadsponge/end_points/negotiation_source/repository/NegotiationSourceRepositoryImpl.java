package br.com.blinkdev.leadsponge.end_points.negotiation_source.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.entity.NegotiationSourceEntity_;
import br.com.blinkdev.leadsponge.end_points.negotiation_source.filter.NegotiationSourceFilter;
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

public class NegotiationSourceRepositoryImpl implements NegotiationSourceRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<NegotiationSourceEntity> filtrar(NegotiationSourceFilter fonteNegociacaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<NegotiationSourceEntity> criteria = builder.createQuery(NegotiationSourceEntity.class);
        Root<NegotiationSourceEntity> root = criteria.from(NegotiationSourceEntity.class);
        Predicate[] predicates = criarRestricoes(fonteNegociacaoFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<NegotiationSourceEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(fonteNegociacaoFilter));
    }

    private Predicate[] criarRestricoes(NegotiationSourceFilter fonteNegociacaoFilter, CriteriaBuilder builder, Root<NegotiationSourceEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (fonteNegociacaoFilter.getName() != null && !fonteNegociacaoFilter.getName().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(NegotiationSourceEntity_.name)), "%" + fonteNegociacaoFilter.getName().toLowerCase() + "%"));
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

    private Long total(NegotiationSourceFilter fonteNegociacaoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<NegotiationSourceEntity> root = criteria.from(NegotiationSourceEntity.class);
        Predicate[] predicates = criarRestricoes(fonteNegociacaoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
