package br.com.blinkdev.leadsponge.services.contato;

import br.com.blinkdev.leadsponge.models.contato.Contato;
import br.com.blinkdev.leadsponge.models.contato.ContatoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ContatoService {
	Contato salvar(Contato contato);

	Contato atualizar(Long id, Contato contato);

	Contato deletar(Long id);

	Contato detalhar(Long id);

	Page<Contato> filtrar(ContatoFilter clienteFilter, Pageable pageable);
}
