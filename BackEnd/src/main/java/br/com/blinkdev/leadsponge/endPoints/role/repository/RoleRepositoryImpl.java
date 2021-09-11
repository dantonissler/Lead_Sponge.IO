package br.com.blinkdev.leadsponge.endPoints.role.repository;


import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity;
import br.com.blinkdev.leadsponge.endPoints.role.entity.RoleEntity_;
import br.com.blinkdev.leadsponge.endPoints.role.filter.RoleFilter;
import br.com.blinkdev.leadsponge.endPoints.role.model.RoleModel;
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

	@Override
	public Page<RoleModel> resumir(RoleFilter usuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RoleModel> criteria = builder.createQuery(RoleModel.class);
		Root<RoleEntity> root = criteria.from(RoleEntity.class);
		criteria.select(builder.construct(RoleModel.class, root.get(RoleEntity_.id), root.get(RoleEntity_.nome)));
		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<RoleModel> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(usuarioFilter));
	}

	private Predicate[] criarRestricoes(RoleFilter roleFilter, CriteriaBuilder builder, Root<RoleEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (roleFilter.getNome() != null && !roleFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(RoleEntity_.nome)), "%" + roleFilter.getNome().toLowerCase() + "%"));
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
