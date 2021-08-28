package br.com.blinkdev.leadsponge.repository.motivoPerda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerda_;
import br.com.blinkdev.leadsponge.repository.Filter.MotivoPerdaFilter;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class MotivoPerdaRepositoryImpl implements MotivoPerdaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<MotivoPerda> filtrar(MotivoPerdaFilter motivoPerdaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<MotivoPerda> criteria = builder.createQuery(MotivoPerda.class);
        Root<MotivoPerda> root = criteria.from(MotivoPerda.class);

        Predicate[] predicates = criarRestricoes(motivoPerdaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<MotivoPerda> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(motivoPerdaFilter));
    }

    private Predicate[] criarRestricoes(MotivoPerdaFilter motivoPerdaFilter, CriteriaBuilder builder, Root<MotivoPerda> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(motivoPerdaFilter.getNome())) {
            predicates.add(builder.like(builder.lower(root.get(MotivoPerda_.nome)), "%" + motivoPerdaFilter.getNome().toLowerCase() + "%"));
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

    private Long total(MotivoPerdaFilter motivoPerdaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<MotivoPerda> root = criteria.from(MotivoPerda.class);
        Predicate[] predicates = criarRestricoes(motivoPerdaFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
