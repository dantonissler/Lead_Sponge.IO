package br.com.blinkdev.leadsponge.endPoints.segmento.repository;

import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity;
import br.com.blinkdev.leadsponge.endPoints.segmento.entity.SegmentoEntity_;
import br.com.blinkdev.leadsponge.endPoints.segmento.filter.SegmentoFilter;
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

public class SegmentoRepositoryImpl implements SegmentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<SegmentoEntity> filtrar(SegmentoFilter segmentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SegmentoEntity> criteria = builder.createQuery(SegmentoEntity.class);
		Root<SegmentoEntity> root = criteria.from(SegmentoEntity.class);
		Predicate[] predicates = criarRestricoes(segmentoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<SegmentoEntity> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(segmentoFilter));
	}

	private Predicate[] criarRestricoes(SegmentoFilter segmentoFilter, CriteriaBuilder builder, Root<SegmentoEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (segmentoFilter.getNome() != null && !segmentoFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(SegmentoEntity_.nome)), "%" + segmentoFilter.getNome().toLowerCase() + "%"));
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

	private Long total(SegmentoFilter segmentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<SegmentoEntity> root = criteria.from(SegmentoEntity.class);
		Predicate[] predicates = criarRestricoes(segmentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
