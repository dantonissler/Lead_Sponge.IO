package br.com.blinkdev.leadsponge.endPoints.customer.service;

import br.com.blinkdev.leadsponge.endPoints.customer.entity.CustomerEntity;
import br.com.blinkdev.leadsponge.endPoints.customer.filter.CustomerFilter;
import br.com.blinkdev.leadsponge.endPoints.customer.repository.CustomerRepository;
import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl extends ErroMessage implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public CustomerEntity salvar(CustomerEntity cliente) {
        cliente.setSegmentos(new ArrayList<>(cliente.getSegmentos()));
        cliente.getContato().forEach(c -> c.setCliente(cliente));
        cliente.getContato().forEach(contato -> contato.getTelefone().forEach(telefone -> telefone.setContato(contato)));
        cliente.getContato().forEach(contato -> contato.getEmail().forEach(email -> email.setContato(contato)));
        cliente.setSeguidores(new ArrayList<>(cliente.getSeguidores()));
        return repository.save(cliente);
    }

    @Override
    public CustomerEntity atualizar(Long id, CustomerEntity cliente) {
        CustomerEntity clienteSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
        if (cliente.getSegmentos() != null) {
            clienteSalvo.getSegmentos().clear();
            clienteSalvo.setSegmentos(cliente.getSegmentos());
        }
        if (cliente.getContato() != null) {
            clienteSalvo.getContato().clear();
            clienteSalvo.setContato(cliente.getContato());
        }
        if (cliente.getSeguidores() != null) {
            clienteSalvo.getSeguidores().clear();
            clienteSalvo.setSeguidores(cliente.getSeguidores());
        }
        if (cliente.getResponsavel() != null) {
            clienteSalvo.setResponsavel(cliente.getResponsavel());
        }
        if (cliente.getNegociacoes() != null) {
            clienteSalvo.getNegociacoes().clear();
            clienteSalvo.setNegociacoes(cliente.getNegociacoes());
        }
//		clienteSalvo.getContato().forEach(contato -> contato.getEmail().clear());
//		clienteSalvo.getContato().forEach(contato -> contato.getTelefone().clear());
//		clienteSalvo.getContato().addAll(cliente.getContato());
//		cliente.getContato().forEach(contatos -> clienteSalvo.getContato().forEach(contato -> contato.getTelefone().addAll(contatos.getTelefone())));
//		cliente.getContato().forEach(contatos -> clienteSalvo.getContato().forEach(contato -> contato.getEmail().addAll(contatos.getEmail())));
//		clienteSalvo.getContato().forEach(contato -> contato.setCliente(clienteSalvo));
//		clienteSalvo.getContato().forEach(contato -> contato.getTelefone().forEach(telefone -> telefone.setContato(contato)));
//		clienteSalvo.getContato().forEach(contato -> contato.getEmail().forEach(email -> email.setContato(contato)));
        BeanUtils.copyProperties(cliente, clienteSalvo, "id", "segmentos", "contatos");
        return repository.save(clienteSalvo);
    }

    @Override
    public Page<CustomerEntity> filtrar(CustomerFilter campanhaFilter, Pageable pageable) {
        return repository.searchWithFilters(campanhaFilter, pageable);
    }

    @Override
    public CustomerEntity deletar(Long id) {
        CustomerEntity campanhaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente"));
        repository.deleteById(id);
        return campanhaSalvo;
    }

    @Override
    public CustomerEntity detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente"));
    }
}
