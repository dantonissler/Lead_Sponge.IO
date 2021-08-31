package br.com.blinkdev.leadsponge.services.cliente;

import br.com.blinkdev.leadsponge.models.cliente.Cliente;
import br.com.blinkdev.leadsponge.models.cliente.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {
	Cliente salvar(Cliente cliente);

	Cliente atualizar(Long id, Cliente cliente);

	Cliente deletar(Long id);

	Cliente detalhar(Long id);

	Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
