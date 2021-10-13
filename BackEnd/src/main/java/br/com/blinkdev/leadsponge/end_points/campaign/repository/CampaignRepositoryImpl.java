package br.com.blinkdev.leadsponge.end_points.campaign.repository;

import br.com.blinkdev.leadsponge.end_points.campaign.filter.CampaignFilters;
import br.com.blinkdev.leadsponge.end_points.campaign.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.end_points.campaign.entity.CampaignEntity_;
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

public class CampaignRepositoryImpl implements CampaignRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<CampaignEntity> searchWithFilters(CampaignFilters campanhaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<CampaignEntity> criteria = builder.createQuery(CampaignEntity.class);
        Root<CampaignEntity> root = criteria.from(CampaignEntity.class);

        Predicate[] predicates = criarRestricoes(campanhaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<CampaignEntity> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(campanhaFilter));
    }

    private Predicate[] criarRestricoes(CampaignFilters campanhaFilter, CriteriaBuilder builder, Root<CampaignEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (campanhaFilter.getName() != null && !campanhaFilter.getName().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(CampaignEntity_.name)), "%" + campanhaFilter.getName().toLowerCase() + "%"));
        }
        if (campanhaFilter.getDescription() != null && !campanhaFilter.getDescription().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(CampaignEntity_.description)), "%" + campanhaFilter.getDescription().toLowerCase() + "%"));
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

    private Long total(CampaignFilters campanhaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<CampaignEntity> root = criteria.from(CampaignEntity.class);
        Predicate[] predicates = criarRestricoes(campanhaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
