package br.com.blinkdev.leadsponge.endpoints.contact.repository;

import br.com.blinkdev.leadsponge.endpoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endpoints.contact.entity.ContactEntity_;
import br.com.blinkdev.leadsponge.endpoints.contact.filter.ContactFilter;
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

public class ContactRepositoryImpl implements ContactRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ContactEntity> searchWithFilters(ContactFilter contatoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ContactEntity> criteria = builder.createQuery(ContactEntity.class);
        Root<ContactEntity> root = criteria.from(ContactEntity.class);

        Predicate[] predicates = criarRestricoes(contatoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ContactEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(contatoFilter));
    }

    private Predicate[] criarRestricoes(ContactFilter contatoFilter, CriteriaBuilder builder, Root<ContactEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (contatoFilter.getNome() != null && !contatoFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ContactEntity_.nome)), "%" + contatoFilter.getNome().toLowerCase() + "%"));
        }
        if (contatoFilter.getCargo() != null && !contatoFilter.getCargo().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ContactEntity_.cargo)), "%" + contatoFilter.getCargo().toLowerCase() + "%"));
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

    private Long total(ContactFilter ContatoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ContactEntity> root = criteria.from(ContactEntity.class);
        Predicate[] predicates = criarRestricoes(ContatoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
