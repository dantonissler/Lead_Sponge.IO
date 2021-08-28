package br.com.blinkdev.leadsponge.repository.negociacaoProduto;

import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProduto_;
import br.com.blinkdev.leadsponge.repository.Filter.NegociacaoProdutoFilter;
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
    public Page<NegociacaoProduto> filtrar(NegociacaoProdutoFilter negociacaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<NegociacaoProduto> criteria = builder.createQuery(NegociacaoProduto.class);
        Root<NegociacaoProduto> root = criteria.from(NegociacaoProduto.class);

        Predicate[] predicates = criarRestricoes(negociacaoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<NegociacaoProduto> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(negociacaoFilter));
    }

    private Predicate[] criarRestricoes(NegociacaoProdutoFilter negociacaoProdutoFilter, CriteriaBuilder builder, Root<NegociacaoProduto> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (negociacaoProdutoFilter.getQuantidade() != null) {
            predicates.add(builder.equal(root.get(NegociacaoProduto_.quantidade), negociacaoProdutoFilter.getQuantidade()));
        }
        if (negociacaoProdutoFilter.getValor() != null) {
            predicates.add(builder.equal(root.get(NegociacaoProduto_.valor), negociacaoProdutoFilter.getValor()));
        }
        if (negociacaoProdutoFilter.getTotal() != null) {
            predicates.add(builder.equal(root.get(NegociacaoProduto_.total), negociacaoProdutoFilter.getTotal()));
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
        Root<NegociacaoProduto> root = criteria.from(NegociacaoProduto.class);
        Predicate[] predicates = criarRestricoes(negociacaoProdutoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
