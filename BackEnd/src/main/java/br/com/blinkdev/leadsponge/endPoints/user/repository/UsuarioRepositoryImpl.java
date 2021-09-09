package br.com.blinkdev.leadsponge.endPoints.user.repository;

import br.com.blinkdev.leadsponge.endPoints.user.entity.UserEntity;
import br.com.blinkdev.leadsponge.endPoints.user.filter.UserFilter;
import br.com.blinkdev.leadsponge.endPoints.user.model.UserModel;
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

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<UserEntity> filtrar(UserFilter usuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
		Root<UserEntity> root = criteria.from(UserEntity.class);

		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<UserEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(usuarioFilter));
	}

	@Override
	public Page<UserModel> resumir(UserFilter usuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UserModel> criteria = builder.createQuery(UserModel.class);
		Root<UserEntity> root = criteria.from(UserEntity.class);
		criteria.select(builder.construct(UserModel.class, root.get(UserEntity_.id), root.get(UserEntity_.username), root.get(UserEntity_.nomeCompleto), root.get(UserEntity_.email), root.get(UserEntity_.enabled)));
		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<UserModel> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(usuarioFilter));
	}

	private Predicate[] criarRestricoes(UserFilter usuarioFilter, CriteriaBuilder builder, Root<UserEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (usuarioFilter.getNomeCompleto().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(UserEntity_.nomeCompleto)), "%" + usuarioFilter.getNomeCompleto().toLowerCase() + "%"));
        }
        if (usuarioFilter.getUsername().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(UserEntity_.username)), "%" + usuarioFilter.getUsername().toLowerCase() + "%"));
        }
        if (usuarioFilter.getEmail().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(UserEntity_.email)), "%" + usuarioFilter.getEmail().toLowerCase() + "%"));
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

	private Long total(UserFilter usuarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<UserEntity> root = criteria.from(UserEntity.class);
		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
