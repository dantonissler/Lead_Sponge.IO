package br.com.blinkdev.leadsponge.endpoints.reasonForLoss.repository;

import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.entity.ReasonForLossEntity;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.entity.ReasonForLossEntity_;
import br.com.blinkdev.leadsponge.endpoints.reasonForLoss.filter.ReasonForLossFilter;
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

public class ReasonForLossRepositoryImpl implements ReasonForLossRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ReasonForLossEntity> filtrar(ReasonForLossFilter motivoPerdaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ReasonForLossEntity> criteria = builder.createQuery(ReasonForLossEntity.class);
        Root<ReasonForLossEntity> root = criteria.from(ReasonForLossEntity.class);
        Predicate[] predicates = criarRestricoes(motivoPerdaFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<ReasonForLossEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(motivoPerdaFilter));
    }

    private Predicate[] criarRestricoes(ReasonForLossFilter motivoPerdaFilter, CriteriaBuilder builder, Root<ReasonForLossEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (motivoPerdaFilter.getNome() != null && !motivoPerdaFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ReasonForLossEntity_.nome)), "%" + motivoPerdaFilter.getNome().toLowerCase() + "%"));
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

    private Long total(ReasonForLossFilter motivoPerdaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ReasonForLossEntity> root = criteria.from(ReasonForLossEntity.class);
        Predicate[] predicates = criarRestricoes(motivoPerdaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
