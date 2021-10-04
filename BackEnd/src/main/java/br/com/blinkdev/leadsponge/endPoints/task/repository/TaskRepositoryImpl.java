package br.com.blinkdev.leadsponge.endPoints.task.repository;

import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity;
import br.com.blinkdev.leadsponge.endPoints.task.entity.TaskEntity_;
import br.com.blinkdev.leadsponge.endPoints.task.filter.TaskFilter;
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

public class TaskRepositoryImpl implements TaskRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<TaskEntity> filtrar(TaskFilter tarefaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TaskEntity> criteria = builder.createQuery(TaskEntity.class);
        Root<TaskEntity> root = criteria.from(TaskEntity.class);
        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<TaskEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(tarefaFilter));
    }

    private Predicate[] criarRestricoes(TaskFilter tarefaFilter, CriteriaBuilder builder, Root<TaskEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (tarefaFilter.getAssunto() != null && !tarefaFilter.getAssunto().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(TaskEntity_.assunto)), "%" + tarefaFilter.getAssunto().toLowerCase() + "%"));
        }
        if (tarefaFilter.getAssunto() != null && !tarefaFilter.getDescricao().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(TaskEntity_.descricao)), "%" + tarefaFilter.getDescricao().toLowerCase() + "%"));
        }
        if (tarefaFilter.getHoraMarcada() != null) {
            predicates.add(builder.equal(root.get(TaskEntity_.horaMarcada), tarefaFilter.getHoraMarcada()));
        }
        if (tarefaFilter.getRealizada() != null) {
            predicates.add(builder.equal(root.get(TaskEntity_.realizda), tarefaFilter.getRealizada()));
        }
        if (tarefaFilter.getHoraRealizada() != null) {
            predicates.add(builder.equal(root.get(TaskEntity_.horaRealizada), tarefaFilter.getHoraRealizada()));
        }
        if (tarefaFilter.getTipo() != null) {
            predicates.add(builder.equal(root.get(TaskEntity_.tipo), tarefaFilter.getTipo()));
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

    private Long total(TaskFilter tarefaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<TaskEntity> root = criteria.from(TaskEntity.class);
        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
