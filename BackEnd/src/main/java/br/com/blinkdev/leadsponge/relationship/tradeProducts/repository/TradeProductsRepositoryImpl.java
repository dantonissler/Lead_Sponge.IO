package br.com.blinkdev.leadsponge.relationship.tradeProducts.repository;

import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.entity.TradeProductsEntity_;
import br.com.blinkdev.leadsponge.relationship.tradeProducts.filter.TradeProductsFilter;
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

public class TradeProductsRepositoryImpl implements TradeProductsRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<TradeProductsEntity> filtrar(TradeProductsFilter negociacaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TradeProductsEntity> criteria = builder.createQuery(TradeProductsEntity.class);
        Root<TradeProductsEntity> root = criteria.from(TradeProductsEntity.class);
        Predicate[] predicates = criarRestricoes(negociacaoFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<TradeProductsEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(negociacaoFilter));
    }

    private Predicate[] criarRestricoes(TradeProductsFilter negociacaoProdutoFilter, CriteriaBuilder builder, Root<TradeProductsEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (negociacaoProdutoFilter.getQuantidade() != null) {
            predicates.add(builder.equal(root.get(TradeProductsEntity_.quantidade), negociacaoProdutoFilter.getQuantidade()));
        }
        if (negociacaoProdutoFilter.getValor() != null) {
            predicates.add(builder.equal(root.get(TradeProductsEntity_.valor), negociacaoProdutoFilter.getValor()));
        }
        if (negociacaoProdutoFilter.getTotal() != null) {
            predicates.add(builder.equal(root.get(TradeProductsEntity_.total), negociacaoProdutoFilter.getTotal()));
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

    private Long total(TradeProductsFilter negociacaoProdutoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<TradeProductsEntity> root = criteria.from(TradeProductsEntity.class);
        Predicate[] predicates = criarRestricoes(negociacaoProdutoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
