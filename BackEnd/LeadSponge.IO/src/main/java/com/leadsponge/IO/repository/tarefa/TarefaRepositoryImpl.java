package com.leadsponge.IO.repository.tarefa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.leadsponge.IO.models.cliente.Cliente_;
import com.leadsponge.IO.models.tarefa.Tarefa;
import com.leadsponge.IO.models.tarefa.Tarefa_;
import com.leadsponge.IO.models.usuario.Usuario_;
import com.leadsponge.IO.repository.Filter.TarefaFilter;
import com.leadsponge.IO.repository.projection.ResumoTarefa;

public class TarefaRepositoryImpl implements TarefaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ResumoTarefa> resumir(TarefaFilter tarefaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoTarefa> criteria = builder.createQuery(ResumoTarefa.class);
		Root<Tarefa> root = criteria.from(Tarefa.class);

		criteria.select(builder.construct(ResumoTarefa.class
				, root.get(Tarefa_.id)
				, root.get(Tarefa_.assunto)
				, root.get(Tarefa_.horaMarcada)
				, root.get(Tarefa_.tipo)
				, root.get(Tarefa_.usuario).get(Usuario_.nomeCompleto)
				, root.get(Tarefa_.cliente).get(Cliente_.nome)));

		Predicate[] predicates = criarRestricoes(tarefaFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<ResumoTarefa> query = manager.createQuery(criteria);
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
		if (!StringUtils.isEmpty(tarefaFilter.getAssunto())) {
			predicates.add(builder.like(builder.lower(root.get(Tarefa_.assunto)),
					"%" + tarefaFilter.getAssunto().toLowerCase() + "%"));
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
