package br.com.blinkdev.leadsponge.endpoints.Product.repository;

import br.com.blinkdev.leadsponge.endpoints.Product.entity.ProductEntity;
import br.com.blinkdev.leadsponge.endpoints.Product.entity.ProductEntity_;
import br.com.blinkdev.leadsponge.endpoints.Product.filter.ProductFilter;
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

public class ProductRepositoryImpl implements ProductRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ProductEntity> filtrar(ProductFilter produtoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteria = builder.createQuery(ProductEntity.class);
        Root<ProductEntity> root = criteria.from(ProductEntity.class);
        Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<ProductEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(produtoFilter));
    }

    private Predicate[] criarRestricoes(ProductFilter produtoFilter, CriteriaBuilder builder, Root<ProductEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (produtoFilter.getNome() != null && !produtoFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ProductEntity_.nome)), "%" + produtoFilter.getNome().toLowerCase() + "%"));
        }
        if (produtoFilter.getDescricao() != null && !produtoFilter.getDescricao().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(ProductEntity_.descricao)), "%" + produtoFilter.getDescricao().toLowerCase() + "%"));
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

    private Long total(ProductFilter produtoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ProductEntity> root = criteria.from(ProductEntity.class);
        Predicate[] predicates = criarRestricoes(produtoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
