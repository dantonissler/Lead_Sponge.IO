package br.com.blinkdev.leadsponge.endPoints.contact.service;

import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endPoints.contact.filter.ContactFilter;
import br.com.blinkdev.leadsponge.endPoints.contact.model.ContactModel;
import br.com.blinkdev.leadsponge.endPoints.contact.modelAssembler.ContactModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.contact.repository.ContactRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Service
public class ContactServiceImpl extends ErroMessage implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactModelAssembler contactModelAssembler;

    @Autowired
    private PagedResourcesAssembler<ContactEntity> assembler;

    @Override
    public ContactModel getById(Long id) {
        log.info("ContactService - getById");
        return contactRepository.findById(id).map(contactModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[contact]"));
    }

    @Override
    public PagedModel<ContactModel> searchWithFilters(ContactFilter contactFilter, Pageable pageable) {
        log.info("ContactService - searchWithFilters");
        return assembler.toModel(contactRepository.searchWithFilters(contactFilter, pageable), contactModelAssembler);
    }

    @Override
    public ContactModel save(ContactEntity contato) {
        log.info("ContactService - save");
        return contactModelAssembler.toModel(contactRepository.save(contato));
    }

    @Override
    public ContactModel patch(Long id, Map<Object, Object> fields) {
        log.info("CampanhaService - patch");
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> notFouldId(id, "[contact]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ContactEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, contactEntity, value);
        });
        return save(contactEntity);
    }

    @Override
    public ContactModel delete(Long id) {
        log.info("CampanhaService - delete");
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(() -> notFouldId(id, "[contact]"));
        contactRepository.deleteById(id);
        return contactModelAssembler.toModel(contactEntity);
    }
}
