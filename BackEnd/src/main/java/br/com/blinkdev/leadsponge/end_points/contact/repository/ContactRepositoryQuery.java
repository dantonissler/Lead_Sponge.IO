package br.com.blinkdev.leadsponge.end_points.contact.repository;

import br.com.blinkdev.leadsponge.end_points.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.end_points.contact.filter.ContactFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepositoryQuery {
    Page<ContactEntity> searchWithFilters(ContactFilter contatoFilter, Pageable pageable);
}
