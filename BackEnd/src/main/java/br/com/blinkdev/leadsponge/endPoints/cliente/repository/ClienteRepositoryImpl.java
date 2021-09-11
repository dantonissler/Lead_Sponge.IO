package br.com.blinkdev.leadsponge.endPoints.cliente.repository;

import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity_;
import br.com.blinkdev.leadsponge.endPoints.cliente.filter.ClienteFilter;
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

public class ClienteRepositoryImpl implements ClienteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ClienteEntity> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ClienteEntity> criteria = builder.createQuery(ClienteEntity.class);
		Root<ClienteEntity> root = criteria.from(ClienteEntity.class);

		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ClienteEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(clienteFilter));
	}

	private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<ClienteEntity> root) {
		List<Predicate> predicates = new ArrayList<>();
        if (clienteFilter.getNome() != null && !clienteFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ClienteEntity_.nome)), "%" + clienteFilter.getNome().toLowerCase() + "%"));
        }
        if (clienteFilter.getUrl() != null && !clienteFilter.getUrl().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ClienteEntity_.url)), "%" + clienteFilter.getUrl().toLowerCase() + "%"));
        }
        if (clienteFilter.getResumo() != null && !clienteFilter.getResumo().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ClienteEntity_.resumo)), "%" + clienteFilter.getResumo().toLowerCase() + "%"));
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

	private Long total(ClienteFilter clienteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ClienteEntity> root = criteria.from(ClienteEntity.class);
		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
