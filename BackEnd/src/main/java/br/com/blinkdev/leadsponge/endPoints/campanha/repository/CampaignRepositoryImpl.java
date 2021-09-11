package br.com.blinkdev.leadsponge.endPoints.campanha.repository;

import br.com.blinkdev.leadsponge.endPoints.campanha.Filter.CampaignFilters;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity;
import br.com.blinkdev.leadsponge.endPoints.campanha.entity.CampaignEntity_;
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
        if (campanhaFilter.getNome() != null && !campanhaFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(CampaignEntity_.nome)), "%" + campanhaFilter.getNome().toLowerCase() + "%"));
        }
        if (campanhaFilter.getDescricao() != null && !campanhaFilter.getDescricao().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(CampaignEntity_.descricao)), "%" + campanhaFilter.getDescricao().toLowerCase() + "%"));
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
