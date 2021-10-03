package br.com.blinkdev.leadsponge.endPoints.customer.service;

import br.com.blinkdev.leadsponge.endPoints.contact.entity.ContactEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.filter.CustomerFilter;
import br.com.blinkdev.leadsponge.endPoints.customer.model.CustomerModel;
import br.com.blinkdev.leadsponge.endPoints.customer.modelAssembler.CustomerModelAssembler;
import br.com.blinkdev.leadsponge.endPoints.customer.repository.CustomerRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Service
public class CustomerServiceImpl extends ErroMessage implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerModelAssembler customerModelAssembler;

    @Autowired
    private PagedResourcesAssembler<CustomerEntity> assembler;

    @Override
    public CustomerModel getById(Long id) {
        log.info("CustomerServiceImpl - getById");
        return customerRepository.findById(id).map(customerModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "the customer"));
    }

    @Override
    public PagedModel<CustomerModel> searchWithFilters(CustomerFilter campanhaFilter, Pageable pageable) {
        log.info("CustomerServiceImpl - searchWithFilters");
        return assembler.toModel(customerRepository.searchWithFilters(campanhaFilter, pageable), customerModelAssembler);
    }

    @Override
    public CustomerModel save(CustomerEntity cliente) {
        log.info("CustomerServiceImpl - save");
        cliente.setSegmentos(new ArrayList<>(cliente.getSegmentos()));
        cliente.getContact().forEach(c -> c.setCustomer(cliente));
        cliente.getContact().forEach(contact -> contact.getPhone().forEach(telefone -> telefone.setContato(contact)));
        cliente.getContact().forEach(contact -> contact.getEmail().forEach(email -> email.setContato(contact)));
        cliente.setSeguidores(new ArrayList<>(cliente.getSeguidores()));
        return customerModelAssembler.toModel(customerRepository.save(cliente));
    }

    @Override
    public CustomerModel patch(Long id, Map<Object, Object> fields) {
        log.info("CustomerServiceImpl - patch");
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ContactEntity.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, customerEntity, value);
        });
//        if (cliente.getSegmentos() != null) {
//            clienteSalvo.getSegmentos().clear();
//            clienteSalvo.setSegmentos(cliente.getSegmentos());
//        }
//        if (cliente.getContato() != null) {
//            clienteSalvo.getContato().clear();
//            clienteSalvo.setContato(cliente.getContato());
//        }
//        if (cliente.getSeguidores() != null) {
//            clienteSalvo.getSeguidores().clear();
//            clienteSalvo.setSeguidores(cliente.getSeguidores());
//        }
//        if (cliente.getResponsavel() != null) {
//            clienteSalvo.setResponsavel(cliente.getResponsavel());
//        }
//        if (cliente.getNegociacoes() != null) {
//            clienteSalvo.getNegociacoes().clear();
//            clienteSalvo.setNegociacoes(cliente.getNegociacoes());
//        }
//		clienteSalvo.getContato().forEach(contato -> contato.getEmail().clear());
//		clienteSalvo.getContato().forEach(contato -> contato.getTelefone().clear());
//		clienteSalvo.getContato().addAll(cliente.getContato());
//		cliente.getContato().forEach(contatos -> clienteSalvo.getContato().forEach(contato -> contato.getTelefone().addAll(contatos.getTelefone())));
//		cliente.getContato().forEach(contatos -> clienteSalvo.getContato().forEach(contato -> contato.getEmail().addAll(contatos.getEmail())));
//		clienteSalvo.getContato().forEach(contato -> contato.setCliente(clienteSalvo));
//		clienteSalvo.getContato().forEach(contato -> contato.getTelefone().forEach(telefone -> telefone.setContato(contato)));
//		clienteSalvo.getContato().forEach(contato -> contato.getEmail().forEach(email -> email.setContato(contato)));
        return save(customerEntity);
    }

    @Override
    public CustomerModel delete(Long id) {
        log.info("CustomerServiceImpl - delete");
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente"));
        customerRepository.deleteById(id);
        return customerModelAssembler.toModel(customerEntity);
    }
}

