package br.com.blinkdev.leadsponge.end_points.role.repository;


import br.com.blinkdev.leadsponge.end_points.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.end_points.role.entity.RoleEntity_;
import br.com.blinkdev.leadsponge.end_points.role.filter.RoleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleRepositoryImpl implements RoleRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<RoleEntity> filtrar(RoleFilter roleFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RoleEntity> criteria = builder.createQuery(RoleEntity.class);
		Root<RoleEntity> root = criteria.from(RoleEntity.class);
		Predicate[] predicates = criarRestricoes(roleFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<RoleEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(roleFilter));
	}

	private Predicate[] criarRestricoes(RoleFilter roleFilter, CriteriaBuilder builder, Root<RoleEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (roleFilter.getName() != null && !roleFilter.getName().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(RoleEntity_.name)), "%" + roleFilter.getName().toLowerCase() + "%"));
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

	private Long total(RoleFilter produtoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<RoleEntity> root = criteria.from(RoleEntity.class);
		Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
