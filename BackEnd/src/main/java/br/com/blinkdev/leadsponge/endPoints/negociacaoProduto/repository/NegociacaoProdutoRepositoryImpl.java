package br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.repository;

import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity;
import br.com.blinkdev.leadsponge.endPoints.negociacaoProduto.entity.NegociacaoProdutoEntity_;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
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

public class NegociacaoProdutoRepositoryImpl implements NegociacaoProdutoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<NegociacaoProdutoEntity> filtrar(NegociacaoProdutoFilter negociacaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<NegociacaoProdutoEntity> criteria = builder.createQuery(NegociacaoProdutoEntity.class);
        Root<NegociacaoProdutoEntity> root = criteria.from(NegociacaoProdutoEntity.class);

        Predicate[] predicates = criarRestricoes(negociacaoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<NegociacaoProdutoEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(negociacaoFilter));
    }

    private Predicate[] criarRestricoes(NegociacaoProdutoFilter negociacaoProdutoFilter, CriteriaBuilder builder, Root<NegociacaoProdutoEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (negociacaoProdutoFilter.getQuantidade() != null) {
            predicates.add(builder.equal(root.get(NegociacaoProdutoEntity_.quantidade), negociacaoProdutoFilter.getQuantidade()));
        }
        if (negociacaoProdutoFilter.getValor() != null) {
            predicates.add(builder.equal(root.get(NegociacaoProdutoEntity_.valor), negociacaoProdutoFilter.getValor()));
        }
        if (negociacaoProdutoFilter.getTotal() != null) {
            predicates.add(builder.equal(root.get(NegociacaoProdutoEntity_.total), negociacaoProdutoFilter.getTotal()));
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

    private Long total(NegociacaoProdutoFilter negociacaoProdutoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<NegociacaoProdutoEntity> root = criteria.from(NegociacaoProdutoEntity.class);
        Predicate[] predicates = criarRestricoes(negociacaoProdutoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
