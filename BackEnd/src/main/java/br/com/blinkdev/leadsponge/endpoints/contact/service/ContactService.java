package br.com.blinkdev.leadsponge.endpoints.contact.service;

import br.com.blinkdev.leadsponge.endpoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endpoints.contact.filter.ContactFilter;
import br.com.blinkdev.leadsponge.endpoints.contact.model.ContactModel;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ContactService {
    ContactModel getById(Long id);

    PagedModel<ContactModel> searchWithFilters(ContactFilter clienteFilter, Pageable pageable);

    ContactModel save(ContactEntity contato);

    ContactModel patch(Long id, Map<Object, Object> fields);

    ContactModel delete(Long id);
}
