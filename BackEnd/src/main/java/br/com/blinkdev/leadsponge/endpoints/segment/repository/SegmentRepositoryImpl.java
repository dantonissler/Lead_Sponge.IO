package br.com.blinkdev.leadsponge.endpoints.segment.repository;

import br.com.blinkdev.leadsponge.endpoints.segment.entity.SegmentEntity;
import br.com.blinkdev.leadsponge.endpoints.segment.entity.SegmentEntity_;
import br.com.blinkdev.leadsponge.endpoints.segment.filter.SegmentFilter;
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

public class SegmentRepositoryImpl implements SegmentRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<SegmentEntity> filtrar(SegmentFilter segmentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<SegmentEntity> criteria = builder.createQuery(SegmentEntity.class);
        Root<SegmentEntity> root = criteria.from(SegmentEntity.class);
        Predicate[] predicates = criarRestricoes(segmentoFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<SegmentEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(segmentoFilter));
    }

    private Predicate[] criarRestricoes(SegmentFilter segmentoFilter, CriteriaBuilder builder, Root<SegmentEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (segmentoFilter.getNome() != null && !segmentoFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(SegmentEntity_.nome)), "%" + segmentoFilter.getNome().toLowerCase() + "%"));
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

    private Long total(SegmentFilter segmentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<SegmentEntity> root = criteria.from(SegmentEntity.class);
        Predicate[] predicates = criarRestricoes(segmentoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
