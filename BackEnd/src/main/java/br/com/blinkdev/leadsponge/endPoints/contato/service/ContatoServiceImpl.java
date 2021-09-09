package br.com.blinkdev.leadsponge.endPoints.contato.service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.endPoints.cliente.entity.ClienteEntity;
import br.com.blinkdev.leadsponge.endPoints.contato.entity.ContatoEntity;
import br.com.blinkdev.leadsponge.endPoints.contato.filter.ContatoFilter;
import br.com.blinkdev.leadsponge.endPoints.cliente.repository.ClienteRepository;
import br.com.blinkdev.leadsponge.endPoints.contato.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContatoServiceImpl extends ErroMessage implements ContatoService {

	@Autowired
	private ContatoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ContatoEntity salvar(ContatoEntity contato) {
		if (!clienteRepository.existsById(contato.getCliente().getId())) {
			throw notFouldId(contato.getId(), " o cliente ");
		}
		contato.getTelefone().forEach(c -> c.setContato(contato));
		contato.getEmail().forEach(c -> c.setContato(contato));
		return repository.save(contato);
	}

	@Override
	public ContatoEntity atualizar(Long id, ContatoEntity contato) {
		ContatoEntity contatoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o contato"));
		ClienteEntity clienteSalva = clienteRepository.findById(contato.getCliente().getId()).orElseThrow(() -> notFouldId(id, "o cliente"));
		contatoSalva.setCliente(clienteSalva);
		contatoSalva.getTelefone().clear();
		contatoSalva.getTelefone().addAll(contato.getTelefone());
		contatoSalva.getTelefone().forEach(c -> c.setContato(contatoSalva));
		contatoSalva.getEmail().clear();
		contatoSalva.getEmail().addAll(contato.getEmail());
		contatoSalva.getEmail().forEach(c -> c.setContato(contatoSalva));
		BeanUtils.copyProperties(contato, contatoSalva, "id", "telefone", "email");
		return repository.save(contatoSalva);
	}

	@Override
	public Page<ContatoEntity> filtrar(ContatoFilter campanhaFilter, Pageable pageable) {
		return repository.filtrar(campanhaFilter, pageable);
	}

	@Override
	public ContatoEntity deletar(Long id) {
		ContatoEntity contatoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "o contato"));
		repository.deleteById(id);
		return contatoSalvo;
	}

	@Override
	public ContatoEntity detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "o contato"));
	}
}
