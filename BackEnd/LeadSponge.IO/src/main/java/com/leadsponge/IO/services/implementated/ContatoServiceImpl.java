package com.leadsponge.IO.services.implementated;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadsponge.IO.errorValidate.ResourceBadRequestException;
import com.leadsponge.IO.models.cliente.Cliente;
import com.leadsponge.IO.models.contato.Contato;
import com.leadsponge.IO.repository.cliente.ClienteRepository;
import com.leadsponge.IO.repository.contato.ContatoRepository;
import com.leadsponge.IO.services.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Contato salvar(Contato contato) {
		validarContato(contato);
		contato.getTelefone().forEach(c -> c.setContato(contato));
		contato.getEmail().forEach(c -> c.setContato(contato));
		return contatoRepository.save(contato);
	}

	@Override
	public Contato atualizar(Long id, Contato contato) {
		Contato contatoSalva = buscarContatoExistente(id);
		contatoSalva.getTelefone().clear();
		contatoSalva.getTelefone().addAll(contato.getTelefone());
		contatoSalva.getTelefone().forEach(c -> c.setContato(contatoSalva));
		contatoSalva.getEmail().clear();
		contatoSalva.getEmail().addAll(contato.getEmail());
		contatoSalva.getEmail().forEach(c -> c.setContato(contatoSalva));
		BeanUtils.copyProperties(contato, contatoSalva, "id", "telefone", "email");
		return contatoRepository.save(contatoSalva);
	}

	private Contato buscarContatoExistente(Long id) {
		Optional<Contato> campanhaSalvo = contatoRepository.findById(id);
		if (!campanhaSalvo.isPresent()) {
			throw new IllegalArgumentException();
		}
		return campanhaSalvo.get();
	}

	private void validarContato(Contato contato) {
		Optional<Cliente> cliente;
		if (contato.getCliente().getId() != null) {
			cliente = clienteRepository.findById(contato.getCliente().getId());
			if (cliente.orElse(null) == null) {
				throw new ResourceBadRequestException("Cliente correspondente ao id: " + contato.getCliente().getId() + " inexistente");
			}
		}
	}
}
