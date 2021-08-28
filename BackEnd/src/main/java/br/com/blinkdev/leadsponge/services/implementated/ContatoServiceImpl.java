package br.com.blinkdev.leadsponge.services.implementated;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.contato.Contato;
import br.com.blinkdev.leadsponge.services.ContatoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.errorValidate.ErroMessage;
import br.com.blinkdev.leadsponge.repository.Filter.ContatoFilter;
import br.com.blinkdev.leadsponge.repository.cliente.ClienteRepository;
import br.com.blinkdev.leadsponge.repository.contato.ContatoRepository;

@Service
public class ContatoServiceImpl extends ErroMessage implements ContatoService {

	@Autowired
	private ContatoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Contato salvar(Contato contato) {
		if (!clienteRepository.existsById(contato.getCliente().getId())) {
			throw notFouldId(contato.getId(), " o cliente ");
		}
		contato.getTelefone().forEach(c -> c.setContato(contato));
		contato.getEmail().forEach(c -> c.setContato(contato));
		return repository.save(contato);
	}

	@Override
	public Contato atualizar(Long id, Contato contato) {
		Contato contatoSalva = repository.findById(id).orElseThrow(() -> notFouldId(id, "o contato"));
		Cliente clienteSalva = clienteRepository.findById(contato.getCliente().getId()).orElseThrow(() -> notFouldId(id, "o cliente"));
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
	public Page<Contato> filtrar(ContatoFilter campanhaFilter, Pageable pageable) {
		return repository.filtrar(campanhaFilter, pageable);
	}

	@Override
	public Contato deletar(Long id) {
		Contato contatoSalvo = repository.findById(id).orElseThrow(() -> notFouldId(id, "o contato"));
		repository.deleteById(id);
		return contatoSalvo;
	}

	@Override
	public Contato detalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> notFouldId(id, "o contato"));
	}
}
