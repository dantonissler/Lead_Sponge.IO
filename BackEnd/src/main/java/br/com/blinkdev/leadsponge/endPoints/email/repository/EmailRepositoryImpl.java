package br.com.blinkdev.leadsponge.endPoints.email.repository;

import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity;
import br.com.blinkdev.leadsponge.endPoints.email.filter.EmailFilter;
import br.com.blinkdev.leadsponge.endPoints.email.entity.EmailEntity_;
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

public class EmailRepositoryImpl implements EmailRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<EmailEntity> filtrar(EmailFilter emailFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EmailEntity> criteria = builder.createQuery(EmailEntity.class);
		Root<EmailEntity> root = criteria.from(EmailEntity.class);

		Predicate[] predicates = criarRestricoes(emailFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<EmailEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(emailFilter));
	}

	private Predicate[] criarRestricoes(EmailFilter emailFilter, CriteriaBuilder builder, Root<EmailEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (emailFilter.getEmail().isBlank()) {
			predicates.add(builder.like(builder.lower(root.get(EmailEntity_.email)), "%" + emailFilter.getEmail().toLowerCase() + "%"));
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

	private Long total(EmailFilter emailFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<EmailEntity> root = criteria.from(EmailEntity.class);
		Predicate[] predicates = criarRestricoes(emailFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
