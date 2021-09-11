package br.com.blinkdev.leadsponge.endPoints.motivoPerda.repository;

import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.entity.MotivoPerdaEntity_;
import br.com.blinkdev.leadsponge.endPoints.motivoPerda.filter.MotivoPerdaFilter;
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

public class MotivoPerdaRepositoryImpl implements MotivoPerdaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<MotivoPerdaEntity> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<MotivoPerdaEntity> criteria = builder.createQuery(MotivoPerdaEntity.class);
        Root<MotivoPerdaEntity> root = criteria.from(MotivoPerdaEntity.class);
        Predicate[] predicates = criarRestricoes(motivoPerdaFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<MotivoPerdaEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(motivoPerdaFilter));
    }

    private Predicate[] criarRestricoes(MotivoPerdaFilter motivoPerdaFilter, CriteriaBuilder builder, Root<MotivoPerdaEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (motivoPerdaFilter.getNome() != null && !motivoPerdaFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(MotivoPerdaEntity_.nome)), "%" + motivoPerdaFilter.getNome().toLowerCase() + "%"));
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

    private Long total(MotivoPerdaFilter motivoPerdaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<MotivoPerdaEntity> root = criteria.from(MotivoPerdaEntity.class);
        Predicate[] predicates = criarRestricoes(motivoPerdaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
