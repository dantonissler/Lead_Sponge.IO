package com.leadsponge.IO.services.implementated;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ErroMessage;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.repository.Filter.ClienteFilter;
import com.leadsponge.IO.repository.cliente.ClienteRepository;
import com.leadsponge.IO.services.ClienteService;

@Service
public class ClienteServiceImpl extends ErroMessage implements ClienteService {
	@Autowired
	private ClienteRepository repository;

	@Override
	public Cliente salvar(Cliente cliente) {
		cliente.setSegmentos(new ArrayList<>(cliente.getSegmentos()));
		cliente.getContato().forEach(c -> c.setCliente(cliente));
		cliente.getContato().forEach(contato -> contato.getTelefone().forEach(telefone -> telefone.setContato(contato)));
		cliente.getContato().forEach(contato -> contato.getEmail().forEach(email -> email.setContato(contato)));
		cliente.setSeguidores(new ArrayList<>(cliente.getSeguidores()));
		return repository.save(cliente);
	}

	@Override
	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "a campanha"));
		clienteSalvo.getSegmentos().clear();
		clienteSalvo.getSegmentos().addAll(cliente.getSegmentos());
		clienteSalvo.setSegmentos(new ArrayList<>(clienteSalvo.getSegmentos()));
		clienteSalvo.getContato().clear();
		clienteSalvo.getContato().forEach(contato -> contato.getEmail().clear());
		clienteSalvo.getContato().forEach(contato -> contato.getTelefone().clear());
		clienteSalvo.getContato().addAll(cliente.getContato());
		cliente.getContato().forEach(contatos -> clienteSalvo.getContato().forEach(contato -> contato.getTelefone().addAll(contatos.getTelefone())));
		cliente.getContato().forEach(contatos -> clienteSalvo.getContato().forEach(contato -> contato.getEmail().addAll(contatos.getEmail())));
		clienteSalvo.getContato().forEach(contato -> contato.setCliente(clienteSalvo));
		clienteSalvo.getContato().forEach(contato -> contato.getTelefone().forEach(telefone -> telefone.setContato(contato)));
		clienteSalvo.getContato().forEach(contato -> contato.getEmail().forEach(email -> email.setContato(contato)));
		BeanUtils.copyProperties(cliente, clienteSalvo, "id", "segmentos", "contatos");
		return repository.save(clienteSalvo);
	}

	@Override
	public Page<Cliente> filtrar(ClienteFilter campanhaFilter, Pageable pageable) {
		return repository.filtrar(campanhaFilter, pageable);
	}

	@Override
	public Cliente deletar(Long id) {
		Cliente campanhaSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente"));
		repository.deleteById(id);
		return campanhaSalvo;
	}

	@Override
	public Cliente detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "o cliente"));
	}
}
