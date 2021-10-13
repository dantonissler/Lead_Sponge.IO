package br.com.blinkdev.leadsponge.end_points.customer.service;

import br.com.blinkdev.leadsponge.end_points.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.end_points.customer.filter.CustomerFilter;
import br.com.blinkdev.leadsponge.end_points.customer.model.CustomerModel;
import br.com.blinkdev.leadsponge.end_points.customer.model_assembler.CustomerModelAssembler;
import br.com.blinkdev.leadsponge.end_points.customer.repository.CustomerRepository;
import br.com.blinkdev.leadsponge.error_validate.ErroMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl extends ErroMessage implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerModelAssembler customerModelAssembler;
    private final PagedResourcesAssembler<CustomerEntity> assembler;

    @Override
    public CustomerModel getById(Long id) {
        log.info("CustomerServiceImpl - getById");
        return customerRepository.findById(id).map(customerModelAssembler::toModel).orElseThrow(() -> notFouldId(id, "[customer]"));
    }

    @Override
    public PagedModel<CustomerModel> searchWithFilters(CustomerFilter campanhaFilter, Pageable pageable) {
        log.info("CustomerService - searchWithFilters");
        return assembler.toModel(customerRepository.searchWithFilters(campanhaFilter, pageable), customerModelAssembler);
    }

    @Override
    @Transactional
    public CustomerModel save(CustomerEntity customerEntity) {
        log.info("CustomerService - save");
        customerEntity.setSegmentos(new ArrayList<>(customerEntity.getSegmentos()));
        customerEntity.getContact().forEach(c -> c.setCustomer(customerEntity));
        customerEntity.getContact().forEach(contact -> contact.getPhone().forEach(telefone -> telefone.setContato(contact)));
        customerEntity.getContact().forEach(contact -> contact.getEmail().forEach(email -> email.setContato(contact)));
        customerEntity.setSeguidores(new ArrayList<>(customerEntity.getSeguidores()));
        return customerModelAssembler.toModel(customerRepository.save(customerEntity));
    }

    @Override
    @Transactional
    public CustomerModel patch(Long id, Map<Object, Object> fields) {
        log.info("CustomerService - patch");
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> notFouldId(id, "[customer]"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(CustomerEntity.class, (String) key);
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
    @Transactional
    public CustomerModel delete(Long id) {
        log.info("CustomerService - delete");
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> notFouldId(id, "[customer]"));
        customerRepository.deleteById(id);
        return customerModelAssembler.toModel(customerEntity);
    }
}

