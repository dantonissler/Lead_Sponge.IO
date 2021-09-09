package br.com.blinkdev.leadsponge.endPoints.produto.repository;

import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.produto.entity.ProdutoEntity_;
import br.com.blinkdev.leadsponge.endPoints.produto.filter.ProdutoFilter;
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

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ProdutoEntity> filtrar(ProdutoFilter produtoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ProdutoEntity> criteria = builder.createQuery(ProdutoEntity.class);
		Root<ProdutoEntity> root = criteria.from(ProdutoEntity.class);

		Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ProdutoEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(produtoFilter));
	}

	private Predicate[] criarRestricoes(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<ProdutoEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (produtoFilter.getNome().isBlank()) {
			predicates.add(builder.like(builder.lower(root.get(ProdutoEntity_.nome)), "%" + produtoFilter.getNome().toLowerCase() + "%"));
		}
		if (produtoFilter.getDescricao().isBlank()) {
			predicates.add(builder.like(builder.lower(root.get(ProdutoEntity_.descricao)), "%" + produtoFilter.getDescricao().toLowerCase() + "%"));
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

	private Long total(ProdutoFilter produtoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ProdutoEntity> root = criteria.from(ProdutoEntity.class);
		Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
