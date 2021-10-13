package br.com.blinkdev.leadsponge.end_points.negotiation.repository;

import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity;
import br.com.blinkdev.leadsponge.end_points.negotiation.entity.NegotiationEntity_;
import br.com.blinkdev.leadsponge.end_points.negotiation.filter.NegotiationFilter;
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

public class NegotiationRepositoryImpl implements NegotiationRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<NegotiationEntity> filtrar(NegotiationFilter negociacaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<NegotiationEntity> criteria = builder.createQuery(NegotiationEntity.class);
        Root<NegotiationEntity> root = criteria.from(NegotiationEntity.class);
        Predicate[] predicates = criarRestricoes(negociacaoFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<NegotiationEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(negociacaoFilter));
    }

    private Predicate[] criarRestricoes(NegotiationFilter negociacaoFilter, CriteriaBuilder builder, Root<NegotiationEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (negociacaoFilter.getName() != null && !negociacaoFilter.getName().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(NegotiationEntity_.name)), "%" + negociacaoFilter.getName().toLowerCase() + "%"));
        }
        if (negociacaoFilter.getEvaluation() != null && !negociacaoFilter.getEvaluation().toString().isBlank()) {
            predicates.add(builder.equal(root.get(NegotiationEntity_.evaluation), negociacaoFilter.getEvaluation()));
        }
        if (negociacaoFilter.getAmount() != null && !negociacaoFilter.getAmount().toString().isBlank()) {
            predicates.add(builder.equal(root.get(NegotiationEntity_.amount), negociacaoFilter.getAmount()));
        }
        if (negociacaoFilter.getMonthlyValue() != null && !negociacaoFilter.getMonthlyValue().toString().isBlank()) {
            predicates.add(builder.equal(root.get(NegotiationEntity_.monthlyValue), negociacaoFilter.getMonthlyValue()));
        }
        if (negociacaoFilter.getSingleValue() != null && !negociacaoFilter.getSingleValue().toString().isBlank()) {
            predicates.add(builder.equal(root.get(NegotiationEntity_.singleValue), negociacaoFilter.getSingleValue()));
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

    private Long total(NegotiationFilter negociacaoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<NegotiationEntity> root = criteria.from(NegotiationEntity.class);
        Predicate[] predicates = criarRestricoes(negociacaoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
