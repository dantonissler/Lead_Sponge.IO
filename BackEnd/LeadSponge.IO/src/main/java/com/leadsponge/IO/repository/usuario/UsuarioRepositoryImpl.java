package com.leadsponge.IO.repository.usuario;

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

import com.leadsponge.IO.models.usuario.Usuario;
import com.leadsponge.IO.models.usuario.Usuario_;
import com.leadsponge.IO.repository.Filter.UsuarioFilter;
import com.leadsponge.IO.repository.projection.ResumoUsuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Usuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(usuarioFilter));
	}

	@Override
	public Page<ResumoUsuario> resumir(UsuarioFilter usuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoUsuario> criteria = builder.createQuery(ResumoUsuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
//		From<Usuario, Role> roleJoin = root.join(Usuario_.roles, JoinType.LEFT);

		criteria.select(builder.construct(ResumoUsuario.class
				, root.get(Usuario_.id)
				, root.get(Usuario_.username)
				, root.get(Usuario_.nomeCompleto)
				, root.get(Usuario_.email)
//				, roleJoin.get(Role_.nome)// TODO : Fazer isso funcionar
				, root.get(Usuario_.enabled)));

		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<ResumoUsuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(usuarioFilter));
	}

	private Predicate[] criarRestricoes(UsuarioFilter usuarioFilter, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(usuarioFilter.getNomeCompleto())) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.nomeCompleto)),
					"%" + usuarioFilter.getNomeCompleto().toLowerCase() + "%"));
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

	private Long total(UsuarioFilter usuarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
