package br.com.blinkdev.leadsponge.services;

import br.com.blinkdev.leadsponge.models.contato.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blinkdev.leadsponge.repository.Filter.ContatoFilter;

@Service
public interface ContatoService {
	Contato salvar(Contato contato);

	Contato atualizar(Long id, Contato contato);

	Contato deletar(Long id);

	Contato detalhar(Long id);

	Page<Contato> filtrar(ContatoFilter clienteFilter, Pageable pageable);
}
