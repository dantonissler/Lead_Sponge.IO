package br.com.blinkdev.leadsponge.repository.email;

import br.com.blinkdev.leadsponge.models.email.Email;
import br.com.blinkdev.leadsponge.models.email.EmailFilter;
import br.com.blinkdev.leadsponge.models.email.Email_;
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

public class EmailRepositoryImpl implements EmailRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Email> filtrar(EmailFilter emailFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Email> criteria = builder.createQuery(Email.class);
		Root<Email> root = criteria.from(Email.class);

		Predicate[] predicates = criarRestricoes(emailFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Email> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(emailFilter));
	}

	private Predicate[] criarRestricoes(EmailFilter emailFilter, CriteriaBuilder builder, Root<Email> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(emailFilter.getEmail())) {
			predicates.add(builder.like(builder.lower(root.get(Email_.email)), "%" + emailFilter.getEmail().toLowerCase() + "%"));
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
		Root<Email> root = criteria.from(Email.class);
		Predicate[] predicates = criarRestricoes(emailFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
