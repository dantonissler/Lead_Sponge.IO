package br.com.blinkdev.leadsponge.repository.tarefa;

import br.com.blinkdev.leadsponge.models.tarefa.Tarefa;
import br.com.blinkdev.leadsponge.models.tarefa.Tarefa_;
import br.com.blinkdev.leadsponge.models.usuario.Usuario_;
import br.com.blinkdev.leadsponge.repository.Filter.TarefaFilter;
import br.com.blinkdev.leadsponge.repository.projection.TarefaResumo;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
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
    public Page<TarefaResumo> resumir(TarefaFilter tarefaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TarefaResumo> criteria = builder.createQuery(TarefaResumo.class);
        Root<Tarefa> root = criteria.from(Tarefa.class);
        criteria.select(builder.construct(TarefaResumo.class, root.get(Tarefa_.id), root.get(Tarefa_.assunto), root.get(Tarefa_.horaMarcada), root.get(Tarefa_.tipo), root.get(Tarefa_.usuario).get(Usuario_.nomeCompleto)));
        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<TarefaResumo> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(tarefaFilter));
    }

    @Override
    public Page<Tarefa> filtrar(TarefaFilter tarefaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Tarefa> criteria = builder.createQuery(Tarefa.class);
        Root<Tarefa> root = criteria.from(Tarefa.class);

        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Tarefa> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(tarefaFilter));
    }

    private Predicate[] criarRestricoes(TarefaFilter tarefaFilter, CriteriaBuilder builder, Root<Tarefa> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(tarefaFilter.getAssunto())) {
            predicates.add(builder.like(builder.lower(root.get(Tarefa_.assunto)), "%" + tarefaFilter.getAssunto().toLowerCase() + "%"));
        }
        if (StringUtils.isNotBlank(tarefaFilter.getDescricao())) {
            predicates.add(builder.like(builder.lower(root.get(Tarefa_.descricao)), "%" + tarefaFilter.getDescricao().toLowerCase() + "%"));
        }
        if (tarefaFilter.getHoraMarcada() != null) {
            predicates.add(builder.equal(root.get(Tarefa_.horaMarcada), tarefaFilter.getHoraMarcada()));
        }
        if (tarefaFilter.getRealizada() != null) {
            predicates.add(builder.equal(root.get(Tarefa_.realizda), tarefaFilter.getRealizada()));
        }
        if (tarefaFilter.getHoraRealizada() != null) {
            predicates.add(builder.equal(root.get(Tarefa_.horaRealizada), tarefaFilter.getHoraRealizada()));
        }
        if (tarefaFilter.getTipo() != null) {
            predicates.add(builder.equal(root.get(Tarefa_.tipo), tarefaFilter.getTipo()));
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
        Root<Tarefa> root = criteria.from(Tarefa.class);
        Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
