package br.com.blinkdev.leadsponge.endPoints.telefone.repository;

import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone;
import br.com.blinkdev.leadsponge.endPoints.telefone.filter.TelefoneFilter;
import br.com.blinkdev.leadsponge.endPoints.telefone.entity.Telefone_;
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

public class TelefoneRepositoryImpl implements TelefoneRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Telefone> filtrar(TelefoneFilter telefoneFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Telefone> criteria = builder.createQuery(Telefone.class);
		Root<Telefone> root = criteria.from(Telefone.class);

		Predicate[] predicates = criarRestricoes(telefoneFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Telefone> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(telefoneFilter));
	}

	private Predicate[] criarRestricoes(TelefoneFilter telefoneFilter, CriteriaBuilder builder, Root<Telefone> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (telefoneFilter.getNumero().isBlank()) {
            predicates.add(builder.equal(root.get(Telefone_.numero), telefoneFilter.getNumero()));
        }
        if (telefoneFilter.getTipo().getDescricao().isBlank()) {
            predicates.add(builder.equal(root.get(Telefone_.tipo), telefoneFilter.getTipo().getDescricao()));
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

	private Long total(TelefoneFilter TelefoneFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Telefone> root = criteria.from(Telefone.class);
		Predicate[] predicates = criarRestricoes(TelefoneFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
