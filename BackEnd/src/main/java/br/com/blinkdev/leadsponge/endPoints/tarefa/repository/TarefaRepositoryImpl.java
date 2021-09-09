package br.com.blinkdev.leadsponge.endPoints.tarefa.repository;

import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity;
import br.com.blinkdev.leadsponge.endPoints.tarefa.entity.TarefaEntity_;
import br.com.blinkdev.leadsponge.endPoints.tarefa.filter.TarefaFilter;
import br.com.blinkdev.leadsponge.endPoints.tarefa.model.TarefaModel;
import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity_;
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

public class TarefaRepositoryImpl implements TarefaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<TarefaModel> resumir(TarefaFilter tarefaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TarefaModel> criteria = builder.createQuery(TarefaModel.class);
        Root<TarefaEntity> root = criteria.from(TarefaEntity.class);
        criteria.select(builder.construct(TarefaModel.class, root.get(TarefaEntity_.id), root.get(TarefaEntity_.assunto), root.get(TarefaEntity_.horaMarcada), root.get(TarefaEntity_.tipo), root.get(TarefaEntity_.usuario).get(UserEntity_.nomeCompleto)));
        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<TarefaModel> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(tarefaFilter));
    }

    @Override
    public Page<TarefaEntity> filtrar(TarefaFilter tarefaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TarefaEntity> criteria = builder.createQuery(TarefaEntity.class);
        Root<TarefaEntity> root = criteria.from(TarefaEntity.class);

        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<TarefaEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(tarefaFilter));
    }

    private Predicate[] criarRestricoes(TarefaFilter tarefaFilter, CriteriaBuilder builder, Root<TarefaEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (tarefaFilter.getAssunto().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(TarefaEntity_.assunto)), "%" + tarefaFilter.getAssunto().toLowerCase() + "%"));
        }
        if (tarefaFilter.getDescricao().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(TarefaEntity_.descricao)), "%" + tarefaFilter.getDescricao().toLowerCase() + "%"));
        }
        if (tarefaFilter.getHoraMarcada() != null) {
            predicates.add(builder.equal(root.get(TarefaEntity_.horaMarcada), tarefaFilter.getHoraMarcada()));
        }
        if (tarefaFilter.getRealizada() != null) {
            predicates.add(builder.equal(root.get(TarefaEntity_.realizda), tarefaFilter.getRealizada()));
        }
        if (tarefaFilter.getHoraRealizada() != null) {
            predicates.add(builder.equal(root.get(TarefaEntity_.horaRealizada), tarefaFilter.getHoraRealizada()));
        }
        if (tarefaFilter.getTipo() != null) {
            predicates.add(builder.equal(root.get(TarefaEntity_.tipo), tarefaFilter.getTipo()));
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

    private Long total(TarefaFilter tarefaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<TarefaEntity> root = criteria.from(TarefaEntity.class);
        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
