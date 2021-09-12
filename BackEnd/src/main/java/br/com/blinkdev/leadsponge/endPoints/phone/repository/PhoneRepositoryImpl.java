package br.com.blinkdev.leadsponge.endPoints.phone.repository;

import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity;
import br.com.blinkdev.leadsponge.endPoints.phone.entity.PhoneEntity_;
import br.com.blinkdev.leadsponge.endPoints.phone.filter.PhoneFilter;
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

public class PhoneRepositoryImpl implements PhoneRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<PhoneEntity> filtrar(PhoneFilter telefoneFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PhoneEntity> criteria = builder.createQuery(PhoneEntity.class);
        Root<PhoneEntity> root = criteria.from(PhoneEntity.class);
        Predicate[] predicates = criarRestricoes(telefoneFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<PhoneEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(telefoneFilter));
    }

    private Predicate[] criarRestricoes(PhoneFilter telefoneFilter, CriteriaBuilder builder, Root<PhoneEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (telefoneFilter.getNumero() != null && !telefoneFilter.getNumero().isBlank()) {
            predicates.add(builder.equal(root.get(PhoneEntity_.numero), telefoneFilter.getNumero()));
        }
        if (telefoneFilter.getTipo() != null && !telefoneFilter.getTipo().getDescricao().isBlank()) {
            predicates.add(builder.equal(root.get(PhoneEntity_.type), telefoneFilter.getTipo().getDescricao()));
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

    private Long total(PhoneFilter TelefoneFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<PhoneEntity> root = criteria.from(PhoneEntity.class);
        Predicate[] predicates = criarRestricoes(TelefoneFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
